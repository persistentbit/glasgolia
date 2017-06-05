package com.persistentbit.glasgolia.db.dbdef;

import java.sql.Types;

/**
 * @author Peter Muys
 * @since 18/08/2015
 */
public enum SqlType{
	sBit(Types.BIT),
	sTinyInt(Types.TINYINT),
	sSmallInt(Types.SMALLINT),
	sInteger(Types.INTEGER),
	sBigint(Types.BIGINT),
	sFloat(Types.FLOAT),
	sReal(Types.REAL),
	sDouble(Types.DOUBLE),
	sNumeric(Types.NUMERIC),
	sDecimal(Types.DECIMAL),
	sChar(Types.CHAR),
	sVarChar(Types.VARCHAR),
	sLongVarChar(Types.LONGVARCHAR),
	sDate(Types.DATE),
	sTime(Types.TIME),
	sTimestamp(Types.TIMESTAMP),
	sBinary(Types.BINARY),
	sVarBinary(Types.VARBINARY),
	sLongVarBinary(Types.LONGVARBINARY),
	sBlob(Types.BLOB),
	sClob(Types.CLOB),
	sNClob(Types.NCLOB),
	sBoolean(Types.BOOLEAN),
	sNChar(Types.NCHAR),
	sNVarChar(Types.NVARCHAR),
	sLongNVarChar(Types.LONGNVARCHAR),
	sSqlXml(Types.SQLXML),
	sJavaObject(Types.JAVA_OBJECT);

	final int      javaSqlType;


	SqlType(int javaSqlType) {
		this.javaSqlType = javaSqlType;
	}

	public static SqlType fromJavaSqlType(int data_type) {
		for(SqlType s : values()) {
			if(s.javaSqlType == data_type) {
				return s;
			}
		}
		throw new RuntimeException("Unknown: " + data_type);
	}

}
