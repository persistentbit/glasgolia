package com.persistentbit.glasgolia.jaql.codegen.posgresql;

import com.persistentbit.core.Nullable;
import com.persistentbit.core.collections.PList;
import com.persistentbit.core.javacodegen.JField;
import com.persistentbit.core.javacodegen.JImport;
import com.persistentbit.core.javacodegen.annotations.CaseClass;
import com.persistentbit.core.javacodegen.annotations.Generated;
import com.persistentbit.core.javacodegen.annotations.NoBuilder;
import com.persistentbit.core.utils.UString;
import com.persistentbit.glasgolia.db.dbdef.DbMetaColumn;

import java.util.Objects;

/**
 * TODOC
 * 
 * @author petermuys
 * @since 16/07/17
 */
@CaseClass
@NoBuilder
public class DbJavaFieldEnum implements DbJavaField {
	private  final	DbMetaColumn	column;
	private  final	String	fieldName;
	private  final	DbEnumType	enumType;
	private  final	String	enumClassName;
	private  final	String	enumPack;
	
	
	@Generated
	public DbJavaFieldEnum(DbMetaColumn column, String fieldName, DbEnumType enumType, String enumClassName, String enumPack){
			this.column = Objects.requireNonNull(column, "column can not be null");
			this.fieldName = Objects.requireNonNull(fieldName, "fieldName can not be null");
			this.enumType = Objects.requireNonNull(enumType, "enumType can not be null");
			this.enumClassName = Objects.requireNonNull(enumClassName, "enumClassName can not be null");
			this.enumPack = Objects.requireNonNull(enumPack, "enumPack can not be null");
	}
	@Override
	public  JField	createJField(){
		JField f = new JField(fieldName, enumClassName);
		if(column.getType().getIsNullable()){
			f = f.asNullable();
		}
		f = f.addImport(new JImport(enumPack + "." + enumClassName));
	    return f;
	}

	@Override
	public PList<DbJavaFieldEnum> getUsedEnums() {
		return PList.val(this);
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
	 * Create a copy of this DbJavaFieldEnum object with a new value for field {@link #column}.<br>
	 * @param column The new value for field {@link #column}
	 * @return A new instance of {@link DbJavaFieldEnum}
	 */
	@Generated
	public  DbJavaFieldEnum	withColumn(DbMetaColumn column){
		return new DbJavaFieldEnum(column, fieldName, enumType, enumClassName, enumPack);
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
	 * Create a copy of this DbJavaFieldEnum object with a new value for field {@link #fieldName}.<br>
	 * @param fieldName The new value for field {@link #fieldName}
	 * @return A new instance of {@link DbJavaFieldEnum}
	 */
	@Generated
	public  DbJavaFieldEnum	withFieldName(String fieldName){
		return new DbJavaFieldEnum(column, fieldName, enumType, enumClassName, enumPack);
	}
	/**
	 * Get the value of field {@link #enumType}.<br>
	 * @return {@link #enumType}
	 */
	@Generated
	public  DbEnumType	getEnumType(){
		return this.enumType;
	}
	/**
	 * Create a copy of this DbJavaFieldEnum object with a new value for field {@link #enumType}.<br>
	 * @param enumType The new value for field {@link #enumType}
	 * @return A new instance of {@link DbJavaFieldEnum}
	 */
	@Generated
	public  DbJavaFieldEnum	withEnumType(DbEnumType enumType){
		return new DbJavaFieldEnum(column, fieldName, enumType, enumClassName, enumPack);
	}
	/**
	 * Get the value of field {@link #enumClassName}.<br>
	 * @return {@link #enumClassName}
	 */
	@Generated
	public  String	getEnumClassName(){
		return this.enumClassName;
	}
	/**
	 * Create a copy of this DbJavaFieldEnum object with a new value for field {@link #enumClassName}.<br>
	 * @param enumClassName The new value for field {@link #enumClassName}
	 * @return A new instance of {@link DbJavaFieldEnum}
	 */
	@Generated
	public  DbJavaFieldEnum	withEnumClassName(String enumClassName){
		return new DbJavaFieldEnum(column, fieldName, enumType, enumClassName, enumPack);
	}
	/**
	 * Get the value of field {@link #enumPack}.<br>
	 * @return {@link #enumPack}
	 */
	@Generated
	public  String	getEnumPack(){
		return this.enumPack;
	}
	/**
	 * Create a copy of this DbJavaFieldEnum object with a new value for field {@link #enumPack}.<br>
	 * @param enumPack The new value for field {@link #enumPack}
	 * @return A new instance of {@link DbJavaFieldEnum}
	 */
	@Generated
	public  DbJavaFieldEnum	withEnumPack(String enumPack){
		return new DbJavaFieldEnum(column, fieldName, enumType, enumClassName, enumPack);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbJavaFieldEnum == false) return false;
		DbJavaFieldEnum obj = (DbJavaFieldEnum)o;
		if(!column.equals(obj.column)) return false;
		if(!fieldName.equals(obj.fieldName)) return false;
		if(!enumType.equals(obj.enumType)) return false;
		if(!enumClassName.equals(obj.enumClassName)) return false;
		if(!enumPack.equals(obj.enumPack)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.column != null ? this.column.hashCode() : 0);
		result = 31 * result + (this.fieldName != null ? this.fieldName.hashCode() : 0);
		result = 31 * result + (this.enumType != null ? this.enumType.hashCode() : 0);
		result = 31 * result + (this.enumClassName != null ? this.enumClassName.hashCode() : 0);
		result = 31 * result + (this.enumPack != null ? this.enumPack.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbJavaFieldEnum[" + 
			"column=" + column + 
			", fieldName=" + (fieldName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(fieldName),32,"...") + '\"') +
			", enumType=" + enumType + 
			", enumClassName=" + (enumClassName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(enumClassName),32,"...") + '\"') +
			", enumPack=" + (enumPack == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(enumPack),32,"...") + '\"') +
			']';
	}
}
