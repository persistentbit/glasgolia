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
public class DbJavaFieldArray implements DbJavaField {
	private  final	DbMetaColumn	column;
	private  final	String	fieldName;
	private  final	DbJavaField	elementField;
	
	
	@Generated
	public DbJavaFieldArray(DbMetaColumn column, String fieldName, DbJavaField elementField){
			this.column = Objects.requireNonNull(column, "column can not be null");
			this.fieldName = Objects.requireNonNull(fieldName, "fieldName can not be null");
			this.elementField = Objects.requireNonNull(elementField, "elementField can not be null");
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
	 * Create a copy of this DbJavaFieldArray object with a new value for field {@link #column}.<br>
	 * @param column The new value for field {@link #column}
	 * @return A new instance of {@link DbJavaFieldArray}
	 */
	@Generated
	public  DbJavaFieldArray	withColumn(DbMetaColumn column){
		return new DbJavaFieldArray(column, fieldName, elementField);
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
	 * Create a copy of this DbJavaFieldArray object with a new value for field {@link #fieldName}.<br>
	 * @param fieldName The new value for field {@link #fieldName}
	 * @return A new instance of {@link DbJavaFieldArray}
	 */
	@Generated
	public  DbJavaFieldArray	withFieldName(String fieldName){
		return new DbJavaFieldArray(column, fieldName, elementField);
	}
	/**
	 * Get the value of field {@link #elementField}.<br>
	 * @return {@link #elementField}
	 */
	@Generated
	public  DbJavaField	getElementField(){
		return this.elementField;
	}
	/**
	 * Create a copy of this DbJavaFieldArray object with a new value for field {@link #elementField}.<br>
	 * @param elementField The new value for field {@link #elementField}
	 * @return A new instance of {@link DbJavaFieldArray}
	 */
	@Generated
	public  DbJavaFieldArray	withElementField(DbJavaField elementField){
		return new DbJavaFieldArray(column, fieldName, elementField);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbJavaFieldArray == false) return false;
		DbJavaFieldArray obj = (DbJavaFieldArray)o;
		if(!column.equals(obj.column)) return false;
		if(!fieldName.equals(obj.fieldName)) return false;
		if(!elementField.equals(obj.elementField)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.column != null ? this.column.hashCode() : 0);
		result = 31 * result + (this.fieldName != null ? this.fieldName.hashCode() : 0);
		result = 31 * result + (this.elementField != null ? this.elementField.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbJavaFieldArray[" + 
			"column=" + column + 
			", fieldName=" + (fieldName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(fieldName),32,"...") + '\"') +
			", elementField=" + elementField + 
			']';
	}
}
