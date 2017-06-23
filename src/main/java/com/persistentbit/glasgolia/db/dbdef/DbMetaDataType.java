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
public class DbMetaDataType extends BaseValueClass{
	private final int     sqlType;
	private final boolean isNullable;
	private final int     columnSize;
	@Nullable
	private final String  dbTypeName;
	private final int     decimalDigits;
	private final boolean isAutoIncrement;

	public DbMetaDataType(int sqlType, boolean isNullable, int columnSize,
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


	public DbMetaDataType(int sqlType){
		this(sqlType,false,0,
			 null,0,false
		);
	}


	public DbMetaDataType withNullable(boolean nullable) {
		return copyWith("isNullable",nullable);
	}

	public DbMetaDataType nullable() {
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

	public DbMetaDataType columnSize(int size){
		return copyWith("columnSize",size);
	}

	public Result<String> getDbTypeName() {
		return Result.result(dbTypeName);
	}

	public DbMetaDataType dbTypeName(String dbTypeName){
		return copyWith("dbTypeName",dbTypeName);
	}

	public int getDecimalDigits() {
		return decimalDigits;
	}

	public DbMetaDataType decimalDigits(int decimalDigits){
		return copyWith("decimalDigits",decimalDigits);
	}

	public boolean isAutoIncrement() {
		return isAutoIncrement;
	}

	public DbMetaDataType withAutoIncrement(boolean isAutoIncrement){
		return copyWith("isAutoIncrement",isAutoIncrement);
	}

	public DbMetaDataType autoIncrement() {
		return withAutoIncrement(true);
	}

	public static final DbMetaDataType sBit           = new DbMetaDataType(Types.BIT);
	public static final DbMetaDataType sTinyInt       = new DbMetaDataType(Types.TINYINT);
	public static final DbMetaDataType sSmallInt      = new DbMetaDataType(Types.SMALLINT);
	public static final DbMetaDataType sInteger       = new DbMetaDataType(Types.INTEGER);
	public static final DbMetaDataType sBigInt        = new DbMetaDataType(Types.BIGINT);
	public static final DbMetaDataType sFloat         = new DbMetaDataType(Types.FLOAT);
	public static final DbMetaDataType sReal          = new DbMetaDataType(Types.REAL);
	public static final DbMetaDataType sDouble        = new DbMetaDataType(Types.DOUBLE);
	public static final DbMetaDataType sNumeric       = new DbMetaDataType(Types.NUMERIC);
	public static final DbMetaDataType sDecimal       = new DbMetaDataType(Types.DECIMAL);
	public static final DbMetaDataType sChar          = new DbMetaDataType(Types.CHAR);
	public static final DbMetaDataType sVarChar       = new DbMetaDataType(Types.VARCHAR);
	public static final DbMetaDataType sLongVarChar   = new DbMetaDataType(Types.LONGNVARCHAR);
	public static final DbMetaDataType sDate          = new DbMetaDataType(Types.DATE);
	public static final DbMetaDataType sTime          = new DbMetaDataType(Types.TIME);
	public static final DbMetaDataType sTimestamp     = new DbMetaDataType(Types.TIMESTAMP);
	public static final DbMetaDataType sBinary        = new DbMetaDataType(Types.BINARY);
	public static final DbMetaDataType sVarBinary     = new DbMetaDataType(Types.VARBINARY);
	public static final DbMetaDataType sLongVarBinary = new DbMetaDataType(Types.LONGVARBINARY);
	public static final DbMetaDataType sBlob          = new DbMetaDataType(Types.BLOB);
	public static final DbMetaDataType sClob          = new DbMetaDataType(Types.CLOB);
	public static final DbMetaDataType sNClob         = new DbMetaDataType(Types.NCLOB);
	public static final DbMetaDataType sBoolean       = new DbMetaDataType(Types.BOOLEAN);
	public static final DbMetaDataType sNChar         = new DbMetaDataType(Types.NCHAR);
	public static final DbMetaDataType sNVarChar      = new DbMetaDataType(Types.NVARCHAR);
	public static final DbMetaDataType sLongNVarChar  = new DbMetaDataType(Types.LONGNVARCHAR);
	public static final DbMetaDataType sSqlXml        = new DbMetaDataType(Types.SQLXML);
	public static final DbMetaDataType sJavaObject    = new DbMetaDataType(Types.JAVA_OBJECT);
	public static final DbMetaDataType sStruct	      = new DbMetaDataType(Types.STRUCT);
}
