package com.persistentbit.glasgolia.jaql.codegen.posgresql;

import com.persistentbit.core.collections.PBitList;
import com.persistentbit.core.collections.PByteList;
import com.persistentbit.core.collections.PList;
import com.persistentbit.core.javacodegen.*;
import com.persistentbit.core.result.Result;
import com.persistentbit.core.utils.UString;
import com.persistentbit.glasgolia.db.dbdef.DbMetaColumn;
import com.persistentbit.glasgolia.db.dbdef.DbMetaDataType;
import com.persistentbit.glasgolia.db.dbdef.DbMetaTable;
import com.persistentbit.glasgolia.db.work.DbRun;
import com.persistentbit.glasgolia.jaql.customtypes.*;

import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

/**
 * TODOC
 *
 * @author petermuys
 * @since 11/07/17
 */
public class PostgresJavaGen{
	private final JavaGenTableSelection	selection;
	private final DbNameTransformer	nameTransformer;
	private final DbRun run;
	private final String rootPackage;

	public PostgresJavaGen(JavaGenTableSelection selection, String rootPackage,DbNameTransformer nameTransformer) {
		this.selection = selection;
		this.rootPackage = rootPackage;
		this.nameTransformer = nameTransformer;
		this.run = selection.getRun();
	}
	public PostgresJavaGen(JavaGenTableSelection selection,String rootPackage){
		this(selection, rootPackage,new DbNameTransformer(name -> UString.firstUpperCase(UString.snake_toCamelCase(name))));

	}

	public JavaGenTableSelection	getSelection() {
		return selection;
	}

	public Result<PList<GeneratedJavaSource>>	generate(){
		PList<DbMetaTable> tables = selection.getTablesAndViews();
		return Result.fromSequence(tables.map(table -> generateStateClass(table))).map(stream -> stream.plist());
	}

	private Result<GeneratedJavaSource> generateStateClass(DbMetaTable table){
		return Result.function(table).code(l -> {
			JClass cls = new JClass(nameTransformer.toJavaName(table));

			for(JField field : table.getColumns().map(col -> generateStateField(table,col))){
				cls = cls.addField(field);
			}
			cls = cls.makeCaseClass();

			String packName = rootPackage
				+ "." + nameTransformer.toJavaName(table.getSchema().getCatalog())
				+ "." + nameTransformer.toJavaName(table.getSchema());


			JJavaFile file = new JJavaFile(packName)
				.addClass(cls);
			return Result.success(file.toJavaSource());
		});
	}
	private JField	generateStateField(DbMetaTable table, DbMetaColumn column){
		DbMetaDataType mt = column.getType();
		String javaName = nameTransformer.toJavaName(table,column);
		switch(mt.getSqlType()){
			case Types.VARBINARY:
			case Types.LONGVARBINARY:
			case Types.BINARY:
			case Types.BLOB:
				return new JField(javaName,PByteList.class);
			case Types.BOOLEAN:
				return new JField(javaName,mt.getIsNullable() ? Boolean.class : boolean.class);

			case Types.BIGINT:
				return new JField(javaName,mt.getIsNullable() ? Long.class : long.class);
			case Types.BIT: {
				if(mt.getColumnSize() == 1){
					return new JField(javaName,mt.getIsNullable() ? Boolean.class : boolean.class);
				}
				return new JField(javaName,PBitList.class);
			}


			case Types.CHAR:
			case Types.NCHAR:
			case Types.VARCHAR:
			case Types.NVARCHAR:
			case Types.CLOB:
			case Types.LONGNVARCHAR:
			case Types.NCLOB:
				return new JField(javaName,String.class);
			case Types.DATE:
				return new JField(javaName,LocalDate.class);

			case Types.NUMERIC:
			case Types.DECIMAL:
				return new JField(javaName,BigDecimal.class);
			case Types.DOUBLE:
				if(mt.getDbTypeName().orElse("").equals("money")){
					return new JField(javaName, Money.class);
				}
				return new JField(javaName,mt.getIsNullable() ? Double.class : double.class);
			case Types.REAL:
			case Types.FLOAT:
				return new JField(javaName,mt.getIsNullable() ? Float.class : float.class);
			case Types.INTEGER:
				return new JField(javaName,mt.getIsNullable() ? Integer.class : int.class);

			case Types.SMALLINT:
				return new JField(javaName,mt.getIsNullable() ? Short.class : short.class);
			case Types.TIMESTAMP:
				return new JField(javaName,LocalDateTime.class);
			case Types.TIME:
				return new JField(javaName,LocalTime.class);
			case Types.TINYINT:
				return new JField(javaName,mt.getIsNullable() ? Byte.class : byte.class);
			case Types.STRUCT:
				return new JField(javaName,nameTransformer.toJavaName(table,column));
			case Types.JAVA_OBJECT:
				throw new RuntimeException("Not Implemented: JAVA_OBJECT for " + column );
			case Types.SQLXML:
				return new JField(javaName,String.class);
			case Types.DISTINCT:
				return new JField(javaName,nameTransformer.toJavaName(table,column));



			case Types.ARRAY:
				return new JField(javaName,PList.class);
			case Types.OTHER:
				switch(column.getType().getDbTypeName().orElse(null)){
					case "interval":
						return new JField(javaName, Interval.class);
					case "cidr":
						return new JField(javaName, Cidr.class);
					case "inet":
						return new JField(javaName, Inet.class);
					case "macaddr":
						return new JField(javaName, Macaddr.class);
					case "uuid":
						return new JField(javaName, UUID.class);
					case "varbit":
						return new JField(javaName, PBitList.class);
					case "tsvector":
						return new JField(javaName, Tsvector.class);
					case "tsquery":
						return new JField(javaName, Tsquery.class);
					case "json":
						return new JField(javaName,"PGobject")
							.addImport(new JImport("org.postgresql.util.PGobject"));
					default:
						throw new RuntimeException("Don't know other type " + column.getType().getDbTypeName() + " for " + column);
				}

			default:
				throw new RuntimeException("Unknown: " + column);
		}
	}
}
