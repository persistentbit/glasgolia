package com.persistentbit.glasgolia.db.dbdef;

import com.persistentbit.core.Nullable;
import com.persistentbit.core.javacodegen.annotations.NoWith;
import com.persistentbit.core.javacodegen.annotations.CaseClass;
import com.persistentbit.core.result.Result;
import com.persistentbit.core.utils.builders.NOT;
import java.util.Optional;
import java.lang.SuppressWarnings;
import com.persistentbit.core.function.ThrowingFunction;
import com.persistentbit.core.utils.UString;
import com.persistentbit.core.javacodegen.annotations.NoGet;
import java.util.Objects;
import com.persistentbit.core.utils.builders.SET;
import com.persistentbit.core.javacodegen.annotations.DefaultValue;
import com.persistentbit.core.javacodegen.annotations.Generated;
import java.util.function.Function;

/**
 * TODOC
 * 
 * @author petermuys
 * @since 3/06/17
 */
@CaseClass
public class DbMetaDataType {
	private  final	int	sqlType;
	@DefaultValue("false")
	private  final	boolean	isNullable;
	@DefaultValue("0")
	private  final	int	columnSize;
	@Nullable
	private  final	String	dbTypeName;
	@DefaultValue("0")
	private  final	int	decimalDigits;
	@DefaultValue("false")
	private  final	boolean	isAutoIncrement;
	
	
	@Generated
	public DbMetaDataType(int sqlType, @Nullable Boolean isNullable, @Nullable Integer columnSize, @Nullable String dbTypeName, @Nullable Integer decimalDigits, @Nullable Boolean isAutoIncrement){
			this.sqlType = Objects.requireNonNull(sqlType, "sqlType can not be null");
			this.isNullable = isNullable == null ? false : isNullable;
			this.columnSize = columnSize == null ? 0 : columnSize;
			this.dbTypeName = dbTypeName;
			this.decimalDigits = decimalDigits == null ? 0 : decimalDigits;
			this.isAutoIncrement = isAutoIncrement == null ? false : isAutoIncrement;
	}
	@Generated
	public DbMetaDataType(int sqlType){
			this(sqlType, null, null, null, null, null);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1> {
		private	int	sqlType;
		private	boolean	isNullable;
		private	int	columnSize;
		private	String	dbTypeName;
		private	int	decimalDigits;
		private	boolean	isAutoIncrement;
		
		
		public  Builder<SET>	setSqlType(int sqlType){
			this.sqlType	=	sqlType;
			return (Builder<SET>)this;
		}
		public  Builder<_T1>	setIsNullable(@Nullable Boolean isNullable){
			this.isNullable	=	isNullable;
			return this;
		}
		public  Builder<_T1>	setColumnSize(@Nullable Integer columnSize){
			this.columnSize	=	columnSize;
			return this;
		}
		public  Builder<_T1>	setDbTypeName(@Nullable String dbTypeName){
			this.dbTypeName	=	dbTypeName;
			return this;
		}
		public  Builder<_T1>	setDecimalDigits(@Nullable Integer decimalDigits){
			this.decimalDigits	=	decimalDigits;
			return this;
		}
		public  Builder<_T1>	setIsAutoIncrement(@Nullable Boolean isAutoIncrement){
			this.isAutoIncrement	=	isAutoIncrement;
			return this;
		}
	}
	/**
	 * Get the value of field {@link #sqlType}.<br>
	 * @return {@link #sqlType}
	 */
	@Generated
	public  int	getSqlType(){
		return this.sqlType;
	}
	/**
	 * Create a copy of this DbMetaDataType object with a new value for field {@link #sqlType}.<br>
	 * @param sqlType The new value for field {@link #sqlType}
	 * @return A new instance of {@link DbMetaDataType}
	 */
	@Generated
	public  DbMetaDataType	withSqlType(int sqlType){
		return new DbMetaDataType(sqlType, isNullable, columnSize, dbTypeName, decimalDigits, isAutoIncrement);
	}
	/**
	 * Get the value of field {@link #isNullable}.<br>
	 * @return {@link #isNullable}
	 */
	@Generated
	public  boolean	getIsNullable(){
		return this.isNullable;
	}
	/**
	 * Create a copy of this DbMetaDataType object with a new value for field {@link #isNullable}.<br>
	 * @param isNullable The new value for field {@link #isNullable}
	 * @return A new instance of {@link DbMetaDataType}
	 */
	@Generated
	public  DbMetaDataType	withIsNullable(@Nullable Boolean isNullable){
		return new DbMetaDataType(sqlType, isNullable, columnSize, dbTypeName, decimalDigits, isAutoIncrement);
	}
	/**
	 * Get the value of field {@link #columnSize}.<br>
	 * @return {@link #columnSize}
	 */
	@Generated
	public  int	getColumnSize(){
		return this.columnSize;
	}
	/**
	 * Create a copy of this DbMetaDataType object with a new value for field {@link #columnSize}.<br>
	 * @param columnSize The new value for field {@link #columnSize}
	 * @return A new instance of {@link DbMetaDataType}
	 */
	@Generated
	public  DbMetaDataType	withColumnSize(@Nullable Integer columnSize){
		return new DbMetaDataType(sqlType, isNullable, columnSize, dbTypeName, decimalDigits, isAutoIncrement);
	}
	/**
	 * Get the value of field {@link #dbTypeName}.<br>
	 * @return {@link #dbTypeName}
	 */
	@Generated
	public  Optional<String>	getDbTypeName(){
		return Optional.ofNullable(this.dbTypeName);
	}
	/**
	 * Create a copy of this DbMetaDataType object with a new value for field {@link #dbTypeName}.<br>
	 * @param dbTypeName The new value for field {@link #dbTypeName}
	 * @return A new instance of {@link DbMetaDataType}
	 */
	@Generated
	public  DbMetaDataType	withDbTypeName(@Nullable String dbTypeName){
		return new DbMetaDataType(sqlType, isNullable, columnSize, dbTypeName, decimalDigits, isAutoIncrement);
	}
	/**
	 * Get the value of field {@link #decimalDigits}.<br>
	 * @return {@link #decimalDigits}
	 */
	@Generated
	public  int	getDecimalDigits(){
		return this.decimalDigits;
	}
	/**
	 * Create a copy of this DbMetaDataType object with a new value for field {@link #decimalDigits}.<br>
	 * @param decimalDigits The new value for field {@link #decimalDigits}
	 * @return A new instance of {@link DbMetaDataType}
	 */
	@Generated
	public  DbMetaDataType	withDecimalDigits(@Nullable Integer decimalDigits){
		return new DbMetaDataType(sqlType, isNullable, columnSize, dbTypeName, decimalDigits, isAutoIncrement);
	}
	/**
	 * Get the value of field {@link #isAutoIncrement}.<br>
	 * @return {@link #isAutoIncrement}
	 */
	@Generated
	public  boolean	getIsAutoIncrement(){
		return this.isAutoIncrement;
	}
	/**
	 * Create a copy of this DbMetaDataType object with a new value for field {@link #isAutoIncrement}.<br>
	 * @param isAutoIncrement The new value for field {@link #isAutoIncrement}
	 * @return A new instance of {@link DbMetaDataType}
	 */
	@Generated
	public  DbMetaDataType	withIsAutoIncrement(@Nullable Boolean isAutoIncrement){
		return new DbMetaDataType(sqlType, isNullable, columnSize, dbTypeName, decimalDigits, isAutoIncrement);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbMetaDataType == false) return false;
		DbMetaDataType obj = (DbMetaDataType)o;
		if(sqlType!= obj.sqlType) return false;
		if(isNullable!= obj.isNullable) return false;
		if(columnSize!= obj.columnSize) return false;
		if(dbTypeName != null ? !dbTypeName.equals(obj.dbTypeName) : obj.dbTypeName!= null) return false;
		if(decimalDigits!= obj.decimalDigits) return false;
		if(isAutoIncrement!= obj.isAutoIncrement) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = this.sqlType;
		result = 31 * result + (this.isNullable ? 1 : 0);
		result = 31 * result + this.columnSize;
		result = 31 * result + (this.dbTypeName != null ? this.dbTypeName.hashCode() : 0);
		result = 31 * result + this.decimalDigits;
		result = 31 * result + (this.isAutoIncrement ? 1 : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbMetaDataType[" + 
			"sqlType=" + sqlType + 
			", isNullable=" + isNullable + 
			", columnSize=" + columnSize + 
			", dbTypeName=" + (dbTypeName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(dbTypeName),32,"...") + '\"') +
			", decimalDigits=" + decimalDigits + 
			", isAutoIncrement=" + isAutoIncrement + 
			']';
	}
	@Generated
	public  DbMetaDataType	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setSqlType(this.sqlType);
		b.setIsNullable(this.isNullable);
		b.setColumnSize(this.columnSize);
		b.setDbTypeName(this.dbTypeName);
		b.setDecimalDigits(this.decimalDigits);
		b.setIsAutoIncrement(this.isAutoIncrement);
		b = updater.apply(b);
		return new DbMetaDataType(b.sqlType, b.isNullable, b.columnSize, b.dbTypeName, b.decimalDigits, b.isAutoIncrement);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static DbMetaDataType	build(ThrowingFunction<Builder<NOT>, Builder<SET>, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new DbMetaDataType(b.sqlType, b.isNullable, b.columnSize, b.dbTypeName, b.decimalDigits, b.isAutoIncrement);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Result<DbMetaDataType>	buildExc(ThrowingFunction<Builder<NOT>, Builder<SET>,Exception> setter){
		return setter.applyResult(new Builder<>()).mapExc(b -> new DbMetaDataType(b.sqlType, b.isNullable, b.columnSize, b.dbTypeName, b.decimalDigits, b.isAutoIncrement));
	}
}
