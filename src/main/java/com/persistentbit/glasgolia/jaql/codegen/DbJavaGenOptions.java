package com.persistentbit.glasgolia.jaql.codegen;

import com.persistentbit.core.collections.PByteList;
import com.persistentbit.core.collections.PList;
import com.persistentbit.core.utils.UString;
import com.persistentbit.glasgolia.db.dbdef.DbMetaColumn;
import com.persistentbit.glasgolia.db.dbdef.DbMetaDataType;
import com.persistentbit.glasgolia.db.dbdef.DbMetaSchema;
import com.persistentbit.glasgolia.db.dbdef.DbMetaTable;

import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

/**
 * TODOC
 *
 * @author petermuys
 * @since 22/06/17
 */
public interface DbJavaGenOptions{
	String	getPackage(DbMetaSchema schema, DbMetaTable table);
	default Optional<String> javaName(DbMetaSchema schema){
		return schema.getName().map(name -> UString.snake_toCamelCase(name.toLowerCase()));
	}
	default String	javaName(DbMetaTable table){
		return UString.snake_toCamelCase(table.getName().toLowerCase());
	}
	default String  javaName(DbMetaTable table, DbMetaColumn column){
		return UString.snake_toCamelCase(column.getName().toLowerCase());
	}
	default DbColumnJavaGen	javaType(DbMetaTable table, DbMetaColumn column){
		DbMetaDataType mt = column.getType();
		switch(mt.getSqlType()){
			case Types.VARBINARY:
			case Types.LONGVARBINARY:
			case Types.BINARY:
			case Types.BLOB: return DbColumnJavaGen.create(PByteList.class);
			case Types.BOOLEAN: return DbColumnJavaGen.create(mt.getIsNullable() ? Boolean.class : boolean.class);
			case Types.BIGINT: return DbColumnJavaGen.create(mt.getIsNullable() ? Long.class : long.class);
			case Types.BIT: {
				if(mt.getColumnSize() == 1){
					return DbColumnJavaGen.create(mt.getIsNullable() ? Boolean.class : boolean.class);
				}
				return DbColumnJavaGen.create(PList.class.getSimpleName() + "<Boolean>",PList.class.getName());
			}
			case Types.CHAR:
			case Types.NCHAR:
			case Types.VARCHAR:
			case Types.NVARCHAR:
			case Types.CLOB:
			case Types.LONGNVARCHAR:
			case Types.NCLOB:
				return DbColumnJavaGen.create(String.class);
			case Types.DATE:
				return DbColumnJavaGen.create(LocalDate.class);

			case Types.NUMERIC:
			case Types.DECIMAL:
				return DbColumnJavaGen.create(BigDecimal.class);
			case Types.DOUBLE:
				return DbColumnJavaGen.create(mt.getIsNullable() ? Double.class : double.class);
			case Types.REAL:
			case Types.FLOAT:
				return DbColumnJavaGen.create(mt.getIsNullable() ? Float.class : float.class);
			case Types.INTEGER:
				return DbColumnJavaGen.create(mt.getIsNullable() ? Integer.class : int.class);

			case Types.SMALLINT:
				return DbColumnJavaGen.create(mt.getIsNullable() ? Short.class : short.class);
			case Types.TIMESTAMP:
				return DbColumnJavaGen.create(LocalDateTime.class);
			case Types.TIME:
				return DbColumnJavaGen.create(LocalTime.class);
			case Types.TINYINT:
				return DbColumnJavaGen.create(mt.getIsNullable() ? Byte.class : byte.class);
			case Types.STRUCT:
				return DbColumnJavaGen.create(javaName(table,column));
			case Types.JAVA_OBJECT:
				throw new RuntimeException("Not Implemented: JAVA_OBJECT for " + column );
			case Types.SQLXML:
				throw new RuntimeException("Not Implemented: SQLXML for " + column );
			case Types.DISTINCT:
				throw new RuntimeException("Not Implemented: DISTINCT for " + column );



			case Types.ARRAY:
				throw new RuntimeException("Not Implemented: ARRAY for " + column );

			default:
				throw new RuntimeException("Unknown: " + column);
		}
	}
}
