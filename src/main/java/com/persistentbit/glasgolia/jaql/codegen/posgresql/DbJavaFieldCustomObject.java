package com.persistentbit.glasgolia.jaql.codegen.posgresql;

import com.persistentbit.core.Nullable;
import com.persistentbit.core.javacodegen.JField;
import com.persistentbit.core.javacodegen.annotations.CaseClass;
import com.persistentbit.glasgolia.db.dbdef.DbMetaColumn;
import java.util.Optional;
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
public class DbJavaFieldCustomObject implements DbJavaField {
	private  final	DbMetaColumn	column;
	private  final	String	fieldName;
	private  final	Class	javaClass;
	@Nullable
	private  final	Class	javaSubType;
	
	
	@Generated
	public DbJavaFieldCustomObject(DbMetaColumn column, String fieldName, Class javaClass, @Nullable Class javaSubType){
			this.column = Objects.requireNonNull(column, "column can not be null");
			this.fieldName = Objects.requireNonNull(fieldName, "fieldName can not be null");
			this.javaClass = Objects.requireNonNull(javaClass, "javaClass can not be null");
			this.javaSubType = javaSubType;
	}
	@Generated
	public DbJavaFieldCustomObject(DbMetaColumn column, String fieldName, Class javaClass){
			this(column, fieldName, javaClass, null);
	}
	@Override
	public  JField	createJField(){
	    JField res = new JField(fieldName, javaClass.getSimpleName());
	    res = res.addImport(javaClass);
	    if (column.getType().getIsNullable()) {
	        res = res.asNullable();
	    }
	    return res;
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
	 * Create a copy of this DbJavaFieldCustomObject object with a new value for field {@link #column}.<br>
	 * @param column The new value for field {@link #column}
	 * @return A new instance of {@link DbJavaFieldCustomObject}
	 */
	@Generated
	public  DbJavaFieldCustomObject	withColumn(DbMetaColumn column){
		return new DbJavaFieldCustomObject(column, fieldName, javaClass, javaSubType);
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
	 * Create a copy of this DbJavaFieldCustomObject object with a new value for field {@link #fieldName}.<br>
	 * @param fieldName The new value for field {@link #fieldName}
	 * @return A new instance of {@link DbJavaFieldCustomObject}
	 */
	@Generated
	public  DbJavaFieldCustomObject	withFieldName(String fieldName){
		return new DbJavaFieldCustomObject(column, fieldName, javaClass, javaSubType);
	}
	/**
	 * Get the value of field {@link #javaClass}.<br>
	 * @return {@link #javaClass}
	 */
	@Generated
	public  Class	getJavaClass(){
		return this.javaClass;
	}
	/**
	 * Create a copy of this DbJavaFieldCustomObject object with a new value for field {@link #javaClass}.<br>
	 * @param javaClass The new value for field {@link #javaClass}
	 * @return A new instance of {@link DbJavaFieldCustomObject}
	 */
	@Generated
	public  DbJavaFieldCustomObject	withJavaClass(Class javaClass){
		return new DbJavaFieldCustomObject(column, fieldName, javaClass, javaSubType);
	}
	/**
	 * Get the value of field {@link #javaSubType}.<br>
	 * @return {@link #javaSubType}
	 */
	@Generated
	public  Optional<Class>	getJavaSubType(){
		return Optional.ofNullable(this.javaSubType);
	}
	/**
	 * Create a copy of this DbJavaFieldCustomObject object with a new value for field {@link #javaSubType}.<br>
	 * @param javaSubType The new value for field {@link #javaSubType}
	 * @return A new instance of {@link DbJavaFieldCustomObject}
	 */
	@Generated
	public  DbJavaFieldCustomObject	withJavaSubType(@Nullable Class javaSubType){
		return new DbJavaFieldCustomObject(column, fieldName, javaClass, javaSubType);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbJavaFieldCustomObject == false) return false;
		DbJavaFieldCustomObject obj = (DbJavaFieldCustomObject)o;
		if(!column.equals(obj.column)) return false;
		if(!fieldName.equals(obj.fieldName)) return false;
		if(!javaClass.equals(obj.javaClass)) return false;
		if(javaSubType != null ? !javaSubType.equals(obj.javaSubType) : obj.javaSubType!= null) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.column != null ? this.column.hashCode() : 0);
		result = 31 * result + (this.fieldName != null ? this.fieldName.hashCode() : 0);
		result = 31 * result + (this.javaClass != null ? this.javaClass.hashCode() : 0);
		result = 31 * result + (this.javaSubType != null ? this.javaSubType.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbJavaFieldCustomObject[" + 
			"column=" + column + 
			", fieldName=" + (fieldName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(fieldName),32,"...") + '\"') +
			", javaClass=" + javaClass + 
			", javaSubType=" + javaSubType + 
			']';
	}
}
