package com.persistentbit.glasgolia.jaql.codegen.posgresql;

import com.persistentbit.core.collections.PBitList;
import com.persistentbit.core.collections.PByteList;
import com.persistentbit.core.collections.PList;
import com.persistentbit.core.javacodegen.GeneratedJavaSource;
import com.persistentbit.core.javacodegen.JClass;
import com.persistentbit.core.javacodegen.JField;
import com.persistentbit.core.javacodegen.JJavaFile;
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
import java.time.ZonedDateTime;
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
		PList<DbJavaTable> javaTables = tables.map(table -> generateJavaTable(table));

		return Result.fromSequence(tables.map(table -> generateStateClass(table))).map(stream -> stream.plist());
	}

	private DbJavaTable	generateJavaTable(DbMetaTable table){
		PList<DbJavaField> fields = table.getColumns().map(col -> getDbJavaField(table, col));
		String packName = rootPackage
			+ "." + nameTransformer.toJavaName(table.getSchema().getCatalog())
			+ "." + nameTransformer.toJavaName(table.getSchema());
		return new DbJavaTable(table,fields,packName + "." + nameTransformer.toJavaName(table));
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

	private DbJavaField	getDbJavaField(DbMetaTable table, DbMetaColumn column){
		DbMetaDataType mt = column.getType();
		String javaName = nameTransformer.toJavaName(table,column);
		switch(mt.getSqlType()){
			case Types.VARBINARY:
			case Types.LONGVARBINARY:
			case Types.BINARY:
			case Types.BLOB:
				return new DbJavaFieldCustomObject(column,javaName,PByteList.class);
			case Types.BOOLEAN:
				return new DbJavaFieldPrimitiveType(column, javaName, boolean.class);

			case Types.BIGINT:
				return new DbJavaFieldPrimitiveType(column, javaName, long.class);
			case Types.BIT: {
				if(mt.getColumnSize() == 1){
					return new DbJavaFieldPrimitiveType(column, javaName, boolean.class);
				}
				return new DbJavaFieldCustomObject(column, javaName, PBitList.class);
			}
			case Types.CHAR:
			case Types.NCHAR:
			case Types.VARCHAR:
			case Types.NVARCHAR:
			case Types.CLOB:
			case Types.LONGNVARCHAR:
			case Types.NCLOB:
				return new DbJavaFieldCustomObject(column, javaName, String.class);
			case Types.DATE:
				return new DbJavaFieldCustomObject(column, javaName, LocalDate.class);

			case Types.NUMERIC:
			case Types.DECIMAL:
				return new DbJavaFieldCustomObject(column, javaName, BigDecimal.class);
			case Types.DOUBLE:
				if(mt.getDbTypeName().orElse("").equals("money")){
					return new DbJavaFieldCustomObject(column, javaName, Money.class);
				}
				return new DbJavaFieldPrimitiveType(column, javaName, double.class);
			case Types.REAL:
			case Types.FLOAT:
				return new DbJavaFieldPrimitiveType(column, javaName, float.class);
			case Types.INTEGER:
				return new DbJavaFieldPrimitiveType(column, javaName, int.class);

			case Types.SMALLINT:
				return new DbJavaFieldPrimitiveType(column, javaName, short.class);
			case Types.TIMESTAMP:
				if(mt.getDbTypeName().get().equals("timestamp")) {
					return new DbJavaFieldCustomObject(column, javaName, LocalDateTime.class);
				} else {
					return new DbJavaFieldCustomObject(column, javaName, ZonedDateTime.class);
				}

			case Types.TIME:
				return new DbJavaFieldCustomObject(column, javaName, LocalTime.class);

			case Types.TINYINT:
				return new DbJavaFieldPrimitiveType(column, javaName, byte.class);
			case Types.STRUCT:
				return new DbJavaFieldStruct(column,javaName);
			case Types.JAVA_OBJECT:
				throw new RuntimeException("Not Implemented: JAVA_OBJECT for " + column );
			case Types.SQLXML:
				return new DbJavaFieldCustomObject(column, javaName, Xml.class);
			case Types.DISTINCT:
				return new DbJavaFieldDomain(column,javaName);



			case Types.ARRAY:
				return createArrayField(javaName, table, column);

			case Types.OTHER:
				switch(column.getType().getDbTypeName().orElse(null)){
					case "interval":
						return new DbJavaFieldCustomObject(column, javaName, Interval.class);
					case "cidr":
						return new DbJavaFieldCustomObject(column, javaName, Cidr.class);
					case "inet":
						return new DbJavaFieldCustomObject(column, javaName, Inet.class);
					case "macaddr":
						return new DbJavaFieldCustomObject(column, javaName, Macaddr.class);
					case "uuid":
						return new DbJavaFieldCustomObject(column, javaName, UUID.class);
					case "varbit":
						return new DbJavaFieldCustomObject(column, javaName, PBitList.class);
					case "tsvector":
						return new DbJavaFieldCustomObject(column, javaName, Tsvector.class);
					case "tsquery":
						return new DbJavaFieldCustomObject(column, javaName, Tsquery.class);
					case "json":
						return new DbJavaFieldCustomObject(column, javaName, Json.class);
					default:
						throw new RuntimeException("Don't know other type " + column.getType().getDbTypeName() + " for " + column);
				}

			default:
				throw new RuntimeException("Unknown: " + column);
		}
	}

	private DbJavaFieldArray	createArrayField(String javaName, DbMetaTable table, DbMetaColumn column){
		switch(column.getType().getDbTypeName().orElse(null)){
			case "_numeric":
			case "_int8":
			case "_int4":
			case "_int2":
			case "_float4":
			case "_float8":
			case "_money":
			case "_enum_test":
			case "_full_name":
			case "_varchar":
			case "_text":
			case "_bpchar":
			case "_bytea":
			case "_timestamp":
			case "_timestamptz":
			case "_date":
			case "_time":
			case "_timetz":
			case "_interval":
			case "_bool":
			case "_cidr":
			case "_inet":
			case "_macaddr":
			case "_bit":
			case "_varbit":
			case "_tsvector":
			case "_tsquery":
			case "_uuid":
			case "_xml":
			case "_json":
			case "us_postal_code":
				return null;
			default:
				throw new RuntimeException("Don't know array element type " + column.getType().getDbTypeName() + " for " + column);
		}
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
						return new JField(javaName,Json.class);
					default:
						throw new RuntimeException("Don't know other type " + column.getType().getDbTypeName() + " for " + column);
				}

			default:
				throw new RuntimeException("Unknown: " + column);
		}
	}
}
