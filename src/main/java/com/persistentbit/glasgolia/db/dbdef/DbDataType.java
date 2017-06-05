package com.persistentbit.glasgolia.db.dbdef;

import com.persistentbit.core.Nullable;
import com.persistentbit.core.result.Result;
import com.persistentbit.core.utils.BaseValueClass;

import java.sql.Types;

/**
 * TODOC
 *
 * @author petermuys
 * @since 3/06/17
 */
public class DbDataType extends BaseValueClass{
	private final int     sqlType;
	private final boolean isNullable;
	private final int     columnSize;
	@Nullable
	private final String  dbTypeName;
	private final int     decimalDigits;
	private final boolean isAutoIncrement;

	public DbDataType(int sqlType, boolean isNullable, int columnSize,
					  String dbTypeName,
					  int decimalDigits,
					  boolean isAutoIncrement
	) {
		this.sqlType = sqlType;
		this.isNullable = isNullable;
		this.columnSize = columnSize;
		this.dbTypeName = dbTypeName;
		this.decimalDigits = decimalDigits;
		this.isAutoIncrement = isAutoIncrement;
	}


	public DbDataType(int sqlType){
		this(sqlType,false,0,
			 null,0,false
		);
	}


	public DbDataType withNullable(boolean nullable) {
		return copyWith("isNullable",nullable);
	}

	public DbDataType nullable() {
		return withNullable(true);
	}

	public int getSqlType() {
		return sqlType;
	}

	public boolean isNullable() {
		return isNullable;
	}


	public int getColumnSize() {
		return columnSize;
	}

	public DbDataType columnSize(int size){
		return copyWith("columnSize",size);
	}

	public Result<String> getDbTypeName() {
		return Result.result(dbTypeName);
	}

	public DbDataType dbTypeName(String dbTypeName){
		return copyWith("dbTypeName",dbTypeName);
	}

	public int getDecimalDigits() {
		return decimalDigits;
	}

	public DbDataType decimalDigits(int decimalDigits){
		return copyWith("decimalDigits",decimalDigits);
	}

	public boolean isAutoIncrement() {
		return isAutoIncrement;
	}

	public DbDataType withAutoIncrement(boolean isAutoIncrement){
		return copyWith("isAutoIncrement",isAutoIncrement);
	}

	public DbDataType autoIncrement() {
		return withAutoIncrement(true);
	}

	public static final DbDataType sBit           = new DbDataType(Types.BIT);
	public static final DbDataType sTinyInt       = new DbDataType(Types.TINYINT);
	public static final DbDataType sSmallInt      = new DbDataType(Types.SMALLINT);
	public static final DbDataType sInteger       = new DbDataType(Types.INTEGER);
	public static final DbDataType sBigInt        = new DbDataType(Types.BIGINT);
	public static final DbDataType sFloat         = new DbDataType(Types.FLOAT);
	public static final DbDataType sReal          = new DbDataType(Types.REAL);
	public static final DbDataType sDouble        = new DbDataType(Types.DOUBLE);
	public static final DbDataType sNumeric       = new DbDataType(Types.NUMERIC);
	public static final DbDataType sDecimal       = new DbDataType(Types.DECIMAL);
	public static final DbDataType sChar          = new DbDataType(Types.CHAR);
	public static final DbDataType sVarChar       = new DbDataType(Types.VARCHAR);
	public static final DbDataType sLongVarChar   = new DbDataType(Types.LONGNVARCHAR);
	public static final DbDataType sDate          = new DbDataType(Types.DATE);
	public static final DbDataType sTime          = new DbDataType(Types.TIME);
	public static final DbDataType sTimestamp     = new DbDataType(Types.TIMESTAMP);
	public static final DbDataType sBinary        = new DbDataType(Types.BINARY);
	public static final DbDataType sVarBinary     = new DbDataType(Types.VARBINARY);
	public static final DbDataType sLongVarBinary = new DbDataType(Types.LONGVARBINARY);
	public static final DbDataType sBlob          = new DbDataType(Types.BLOB);
	public static final DbDataType sClob          = new DbDataType(Types.CLOB);
	public static final DbDataType sNClob         = new DbDataType(Types.NCLOB);
	public static final DbDataType sBoolean       = new DbDataType(Types.BOOLEAN);
	public static final DbDataType sNChar         = new DbDataType(Types.NCHAR);
	public static final DbDataType sNVarChar      = new DbDataType(Types.NVARCHAR);
	public static final DbDataType sLongNVarChar  = new DbDataType(Types.LONGNVARCHAR);
	public static final DbDataType sSqlXml        = new DbDataType(Types.SQLXML);
	public static final DbDataType sJavaObject    = new DbDataType(Types.JAVA_OBJECT);
}
