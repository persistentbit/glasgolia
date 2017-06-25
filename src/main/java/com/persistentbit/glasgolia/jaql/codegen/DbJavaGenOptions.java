package com.persistentbit.glasgolia.jaql.codegen;

import com.persistentbit.core.collections.PByteList;
import com.persistentbit.core.collections.PList;
import com.persistentbit.core.utils.UString;
import com.persistentbit.glasgolia.db.dbdef.DbMetaColumn;
import com.persistentbit.glasgolia.db.dbdef.DbMetaDataType;
import com.persistentbit.glasgolia.db.dbdef.DbMetaSchema;
import com.persistentbit.glasgolia.db.dbdef.DbMetaTable;

import java.sql.Types;

/**
 * TODOC
 *
 * @author petermuys
 * @since 22/06/17
 */
public interface DbJavaGenOptions{
	String	getPackage(DbMetaSchema schema, DbMetaTable table);
	default String	javaName(DbMetaTable table){
		return UString.snake_toCamelCase(table.getName().toLowerCase());
	}
	default String  javaName(DbMetaTable table, DbMetaColumn column){
		return UString.snake_toCamelCase(column.getName().toLowerCase());
	}
	default String	javaType(DbMetaTable table, DbMetaColumn column){
		DbMetaDataType mt = column.getType();
		switch(mt.getSqlType()){
			case Types.VARBINARY:
			case Types.LONGVARBINARY:
			case Types.BINARY:
			case Types.BLOB: return PByteList.class.getSimpleName();
			case Types.BOOLEAN: return mt.getIsNullable() ? Boolean.class.getSimpleName() : boolean.class.getSimpleName();
			case Types.BIGINT: return mt.getIsNullable() ? Long.class.getSimpleName() : long.class.getSimpleName();
			case Types.BIT: {
				if(mt.getColumnSize() == 1){
					return mt.getIsNullable() ? Boolean.class.getSimpleName() : boolean.class.getSimpleName();
				}
				return PList.class.getSimpleName() + "<Boolean>";
			}
			case Types.CHAR:
			case Types.NCHAR:
			case Types.VARCHAR:
			case Types.NVARCHAR:
			case Types.CLOB:
			case Types.LONGNVARCHAR:
			case Types.NCLOB:
				return String.class.getSimpleName();
			case Types.DATE:
			case Types.DECIMAL:
			case Types.DOUBLE:
			case Types.FLOAT:
			case Types.INTEGER:
			case Types.JAVA_OBJECT:
			case Types.NUMERIC:
			case Types.REAL:
			case Types.SMALLINT:
			case Types.SQLXML:
			case Types.TIME:
			case Types.TIMESTAMP:
			case Types.TINYINT:
			case Types.STRUCT:
			case Types.ARRAY:
				case Types.
		}
	}
}
