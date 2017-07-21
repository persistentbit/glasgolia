package com.persistentbit.glasgolia.jaql.codegen.posgresql;

import com.persistentbit.core.Nullable;
import com.persistentbit.core.javacodegen.JField;
import com.persistentbit.core.javacodegen.JImport;
import com.persistentbit.core.javacodegen.annotations.CaseClass;
import com.persistentbit.core.javacodegen.annotations.Generated;
import com.persistentbit.core.javacodegen.annotations.NoBuilder;
import com.persistentbit.core.utils.UString;
import com.persistentbit.glasgolia.db.dbdef.DbMetaColumn;
import com.persistentbit.glasgolia.db.dbdef.DbMetaTable;

import java.util.Objects;

/**
 * TODOC
 * 
 * @author petermuys
 * @since 15/07/17
 */
@CaseClass
@NoBuilder
public class DbJavaFieldStruct implements DbJavaField {
	private  final	DbMetaColumn	column;
	private  final	String	fieldName;
	private  final	DbMetaTable	customTypeDbMeta;
	private  final	String	javaClassName;
	private  final	String	javaPackageName;
	
	
	@Generated
	public DbJavaFieldStruct(DbMetaColumn column, String fieldName, DbMetaTable customTypeDbMeta, String javaClassName, String javaPackageName){
			this.column = Objects.requireNonNull(column, "column can not be null");
			this.fieldName = Objects.requireNonNull(fieldName, "fieldName can not be null");
			this.customTypeDbMeta = Objects.requireNonNull(customTypeDbMeta, "customTypeDbMeta can not be null");
			this.javaClassName = Objects.requireNonNull(javaClassName, "javaClassName can not be null");
			this.javaPackageName = Objects.requireNonNull(javaPackageName, "javaPackageName can not be null");
	}
	@Override
	public  JField	createJField(){
	    JField res = new JField(fieldName, javaClassName);
	    res = res.addImport(new JImport(javaPackageName + "." + javaClassName));
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
	 * Create a copy of this DbJavaFieldStruct object with a new value for field {@link #column}.<br>
	 * @param column The new value for field {@link #column}
	 * @return A new instance of {@link DbJavaFieldStruct}
	 */
	@Generated
	public  DbJavaFieldStruct	withColumn(DbMetaColumn column){
		return new DbJavaFieldStruct(column, fieldName, customTypeDbMeta, javaClassName, javaPackageName);
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
	 * Create a copy of this DbJavaFieldStruct object with a new value for field {@link #fieldName}.<br>
	 * @param fieldName The new value for field {@link #fieldName}
	 * @return A new instance of {@link DbJavaFieldStruct}
	 */
	@Generated
	public  DbJavaFieldStruct	withFieldName(String fieldName){
		return new DbJavaFieldStruct(column, fieldName, customTypeDbMeta, javaClassName, javaPackageName);
	}
	/**
	 * Get the value of field {@link #customTypeDbMeta}.<br>
	 * @return {@link #customTypeDbMeta}
	 */
	@Generated
	public  DbMetaTable	getCustomTypeDbMeta(){
		return this.customTypeDbMeta;
	}
	/**
	 * Create a copy of this DbJavaFieldStruct object with a new value for field {@link #customTypeDbMeta}.<br>
	 * @param customTypeDbMeta The new value for field {@link #customTypeDbMeta}
	 * @return A new instance of {@link DbJavaFieldStruct}
	 */
	@Generated
	public  DbJavaFieldStruct	withCustomTypeDbMeta(DbMetaTable customTypeDbMeta){
		return new DbJavaFieldStruct(column, fieldName, customTypeDbMeta, javaClassName, javaPackageName);
	}
	/**
	 * Get the value of field {@link #javaClassName}.<br>
	 * @return {@link #javaClassName}
	 */
	@Generated
	public  String	getJavaClassName(){
		return this.javaClassName;
	}
	/**
	 * Create a copy of this DbJavaFieldStruct object with a new value for field {@link #javaClassName}.<br>
	 * @param javaClassName The new value for field {@link #javaClassName}
	 * @return A new instance of {@link DbJavaFieldStruct}
	 */
	@Generated
	public  DbJavaFieldStruct	withJavaClassName(String javaClassName){
		return new DbJavaFieldStruct(column, fieldName, customTypeDbMeta, javaClassName, javaPackageName);
	}
	/**
	 * Get the value of field {@link #javaPackageName}.<br>
	 * @return {@link #javaPackageName}
	 */
	@Generated
	public  String	getJavaPackageName(){
		return this.javaPackageName;
	}
	/**
	 * Create a copy of this DbJavaFieldStruct object with a new value for field {@link #javaPackageName}.<br>
	 * @param javaPackageName The new value for field {@link #javaPackageName}
	 * @return A new instance of {@link DbJavaFieldStruct}
	 */
	@Generated
	public  DbJavaFieldStruct	withJavaPackageName(String javaPackageName){
		return new DbJavaFieldStruct(column, fieldName, customTypeDbMeta, javaClassName, javaPackageName);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbJavaFieldStruct == false) return false;
		DbJavaFieldStruct obj = (DbJavaFieldStruct)o;
		if(!column.equals(obj.column)) return false;
		if(!fieldName.equals(obj.fieldName)) return false;
		if(!customTypeDbMeta.equals(obj.customTypeDbMeta)) return false;
		if(!javaClassName.equals(obj.javaClassName)) return false;
		if(!javaPackageName.equals(obj.javaPackageName)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.column != null ? this.column.hashCode() : 0);
		result = 31 * result + (this.fieldName != null ? this.fieldName.hashCode() : 0);
		result = 31 * result + (this.customTypeDbMeta != null ? this.customTypeDbMeta.hashCode() : 0);
		result = 31 * result + (this.javaClassName != null ? this.javaClassName.hashCode() : 0);
		result = 31 * result + (this.javaPackageName != null ? this.javaPackageName.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbJavaFieldStruct[" + 
			"column=" + column + 
			", fieldName=" + (fieldName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(fieldName),32,"...") + '\"') +
			", customTypeDbMeta=" + customTypeDbMeta + 
			", javaClassName=" + (javaClassName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(javaClassName),32,"...") + '\"') +
			", javaPackageName=" + (javaPackageName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(javaPackageName),32,"...") + '\"') +
			']';
	}
}
