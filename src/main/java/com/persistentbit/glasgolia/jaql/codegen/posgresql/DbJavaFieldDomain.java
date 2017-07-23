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
import com.persistentbit.glasgolia.db.dbdef.DbMetaUDT;

import java.util.Objects;

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
	private  final	DbMetaUDT	udtMeta;
	private  final	String	fieldName;
	private  final	String	udtClassName;
	private  final	String	udtPack;
	
	
	@Generated
	public DbJavaFieldDomain(DbMetaColumn column, DbMetaUDT udtMeta, String fieldName, String udtClassName, String udtPack){
			this.column = Objects.requireNonNull(column, "column can not be null");
			this.udtMeta = Objects.requireNonNull(udtMeta, "udtMeta can not be null");
			this.fieldName = Objects.requireNonNull(fieldName, "fieldName can not be null");
			this.udtClassName = Objects.requireNonNull(udtClassName, "udtClassName can not be null");
			this.udtPack = Objects.requireNonNull(udtPack, "udtPack can not be null");
	}
	@Override
	public  JField	createJField(){
	    JField res = new JField(fieldName, udtClassName);
	    res = res.addImport(new JImport(udtPack + "." + udtClassName));
	    if (column.getType().getIsNullable()) {
	        res = res.asNullable();
	    }
	    return res;
	}

	@Override
	public PList<DbJavaFieldDomain> getDomains() {
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
	 * Create a copy of this DbJavaFieldDomain object with a new value for field {@link #column}.<br>
	 * @param column The new value for field {@link #column}
	 * @return A new instance of {@link DbJavaFieldDomain}
	 */
	@Generated
	public  DbJavaFieldDomain	withColumn(DbMetaColumn column){
		return new DbJavaFieldDomain(column, udtMeta, fieldName, udtClassName, udtPack);
	}
	/**
	 * Get the value of field {@link #udtMeta}.<br>
	 * @return {@link #udtMeta}
	 */
	@Generated
	public  DbMetaUDT	getUdtMeta(){
		return this.udtMeta;
	}
	/**
	 * Create a copy of this DbJavaFieldDomain object with a new value for field {@link #udtMeta}.<br>
	 * @param udtMeta The new value for field {@link #udtMeta}
	 * @return A new instance of {@link DbJavaFieldDomain}
	 */
	@Generated
	public  DbJavaFieldDomain	withUdtMeta(DbMetaUDT udtMeta){
		return new DbJavaFieldDomain(column, udtMeta, fieldName, udtClassName, udtPack);
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
		return new DbJavaFieldDomain(column, udtMeta, fieldName, udtClassName, udtPack);
	}
	/**
	 * Get the value of field {@link #udtClassName}.<br>
	 * @return {@link #udtClassName}
	 */
	@Generated
	public  String	getUdtClassName(){
		return this.udtClassName;
	}
	/**
	 * Create a copy of this DbJavaFieldDomain object with a new value for field {@link #udtClassName}.<br>
	 * @param udtClassName The new value for field {@link #udtClassName}
	 * @return A new instance of {@link DbJavaFieldDomain}
	 */
	@Generated
	public  DbJavaFieldDomain	withUdtClassName(String udtClassName){
		return new DbJavaFieldDomain(column, udtMeta, fieldName, udtClassName, udtPack);
	}
	/**
	 * Get the value of field {@link #udtPack}.<br>
	 * @return {@link #udtPack}
	 */
	@Generated
	public  String	getUdtPack(){
		return this.udtPack;
	}
	/**
	 * Create a copy of this DbJavaFieldDomain object with a new value for field {@link #udtPack}.<br>
	 * @param udtPack The new value for field {@link #udtPack}
	 * @return A new instance of {@link DbJavaFieldDomain}
	 */
	@Generated
	public  DbJavaFieldDomain	withUdtPack(String udtPack){
		return new DbJavaFieldDomain(column, udtMeta, fieldName, udtClassName, udtPack);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbJavaFieldDomain == false) return false;
		DbJavaFieldDomain obj = (DbJavaFieldDomain)o;
		if(!column.equals(obj.column)) return false;
		if(!udtMeta.equals(obj.udtMeta)) return false;
		if(!fieldName.equals(obj.fieldName)) return false;
		if(!udtClassName.equals(obj.udtClassName)) return false;
		if(!udtPack.equals(obj.udtPack)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.column != null ? this.column.hashCode() : 0);
		result = 31 * result + (this.udtMeta != null ? this.udtMeta.hashCode() : 0);
		result = 31 * result + (this.fieldName != null ? this.fieldName.hashCode() : 0);
		result = 31 * result + (this.udtClassName != null ? this.udtClassName.hashCode() : 0);
		result = 31 * result + (this.udtPack != null ? this.udtPack.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbJavaFieldDomain[" + 
			"column=" + column + 
			", udtMeta=" + udtMeta + 
			", fieldName=" + (fieldName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(fieldName),32,"...") + '\"') +
			", udtClassName=" + (udtClassName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(udtClassName),32,"...") + '\"') +
			", udtPack=" + (udtPack == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(udtPack),32,"...") + '\"') +
			']';
	}
}
