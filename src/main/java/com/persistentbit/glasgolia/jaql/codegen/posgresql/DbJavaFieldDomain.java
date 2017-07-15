package com.persistentbit.glasgolia.jaql.codegen.posgresql;

import com.persistentbit.core.Nullable;
import com.persistentbit.core.javacodegen.JField;
import com.persistentbit.core.javacodegen.annotations.CaseClass;
import com.persistentbit.glasgolia.db.dbdef.DbMetaColumn;
import com.persistentbit.core.utils.UString;
import com.persistentbit.core.javacodegen.annotations.NoBuilder;
import java.util.Objects;
import com.persistentbit.core.javacodegen.annotations.Generated;

/**
 * TODOC
 * 
 * @author petermuys
 * @since 15/07/17
 */
@CaseClass
@NoBuilder
public class DbJavaFieldDomain implements DbJavaField {
	private  final	DbMetaColumn	column;
	private  final	String	fieldName;
	
	
	@Generated
	public DbJavaFieldDomain(DbMetaColumn column, String fieldName){
			this.column = Objects.requireNonNull(column, "column can not be null");
			this.fieldName = Objects.requireNonNull(fieldName, "fieldName can not be null");
	}
	@Override
	public  JField	createJField(){
	    return null;
	}
	/**
	 * Get the value of field {@link #column}.<br>
	 * @return {@link #column}
	 */
	@Generated
	public  DbMetaColumn	getColumn(){
		return this.column;
	}
	/**
	 * Create a copy of this DbJavaFieldDomain object with a new value for field {@link #column}.<br>
	 * @param column The new value for field {@link #column}
	 * @return A new instance of {@link DbJavaFieldDomain}
	 */
	@Generated
	public  DbJavaFieldDomain	withColumn(DbMetaColumn column){
		return new DbJavaFieldDomain(column, fieldName);
	}
	/**
	 * Get the value of field {@link #fieldName}.<br>
	 * @return {@link #fieldName}
	 */
	@Generated
	public  String	getFieldName(){
		return this.fieldName;
	}
	/**
	 * Create a copy of this DbJavaFieldDomain object with a new value for field {@link #fieldName}.<br>
	 * @param fieldName The new value for field {@link #fieldName}
	 * @return A new instance of {@link DbJavaFieldDomain}
	 */
	@Generated
	public  DbJavaFieldDomain	withFieldName(String fieldName){
		return new DbJavaFieldDomain(column, fieldName);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbJavaFieldDomain == false) return false;
		DbJavaFieldDomain obj = (DbJavaFieldDomain)o;
		if(!column.equals(obj.column)) return false;
		if(!fieldName.equals(obj.fieldName)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.column != null ? this.column.hashCode() : 0);
		result = 31 * result + (this.fieldName != null ? this.fieldName.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbJavaFieldDomain[" + 
			"column=" + column + 
			", fieldName=" + (fieldName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(fieldName),32,"...") + '\"') +
			']';
	}
}
