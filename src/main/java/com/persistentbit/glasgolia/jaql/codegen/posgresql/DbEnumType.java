package com.persistentbit.glasgolia.jaql.codegen.posgresql;

import com.persistentbit.core.Nullable;
import com.persistentbit.core.collections.PList;
import com.persistentbit.core.javacodegen.annotations.CaseClass;
import com.persistentbit.core.tuples.Tuple2;
import com.persistentbit.core.utils.UString;
import com.persistentbit.core.javacodegen.annotations.NoBuilder;
import java.util.Objects;
import com.persistentbit.core.javacodegen.annotations.DefaultValue;
import com.persistentbit.core.javacodegen.annotations.Generated;
import com.persistentbit.glasgolia.db.dbdef.DbMetaSchema;

/**
 * TODOC
 * 
 * @author petermuys
 * @since 16/07/17
 */
@CaseClass
@NoBuilder
public class DbEnumType {
	private  final	DbMetaSchema	schema;
	private  final	String	name;
	@DefaultValue("PList.empty()")
	private  final	PList<Tuple2<String,String>>	valueAndJavaNameList;
	private  final	String	javaPackageName;
	private  final	String	javaClassName;
	
	
	@Generated
	public DbEnumType(DbMetaSchema schema, String name, @Nullable PList<Tuple2<String,String>> valueAndJavaNameList, String javaPackageName, String javaClassName){
			this.schema = Objects.requireNonNull(schema, "schema can not be null");
			this.name = Objects.requireNonNull(name, "name can not be null");
			this.valueAndJavaNameList = valueAndJavaNameList == null ? PList.empty() : valueAndJavaNameList;
			this.javaPackageName = Objects.requireNonNull(javaPackageName, "javaPackageName can not be null");
			this.javaClassName = Objects.requireNonNull(javaClassName, "javaClassName can not be null");
	}
	@Generated
	public DbEnumType(DbMetaSchema schema, String name, String javaPackageName, String javaClassName){
			this(schema, name, null, javaPackageName, javaClassName);
	}
	public  DbEnumType	addValue(String dbName, String javaName){
	    return withValueAndJavaNameList(valueAndJavaNameList.plus(Tuple2.of(dbName, javaName)));
	}
	/**
	 * Get the value of field {@link #schema}.<br>
	 * @return {@link #schema}
	 */
	@Generated
	public  DbMetaSchema	getSchema(){
		return this.schema;
	}
	/**
	 * Create a copy of this DbEnumType object with a new value for field {@link #schema}.<br>
	 * @param schema The new value for field {@link #schema}
	 * @return A new instance of {@link DbEnumType}
	 */
	@Generated
	public  DbEnumType	withSchema(DbMetaSchema schema){
		return new DbEnumType(schema, name, valueAndJavaNameList, javaPackageName, javaClassName);
	}
	/**
	 * Get the value of field {@link #name}.<br>
	 * @return {@link #name}
	 */
	@Generated
	public  String	getName(){
		return this.name;
	}
	/**
	 * Create a copy of this DbEnumType object with a new value for field {@link #name}.<br>
	 * @param name The new value for field {@link #name}
	 * @return A new instance of {@link DbEnumType}
	 */
	@Generated
	public  DbEnumType	withName(String name){
		return new DbEnumType(schema, name, valueAndJavaNameList, javaPackageName, javaClassName);
	}
	/**
	 * Get the value of field {@link #valueAndJavaNameList}.<br>
	 * @return {@link #valueAndJavaNameList}
	 */
	@Generated
	public  PList<Tuple2<String,String>>	getValueAndJavaNameList(){
		return this.valueAndJavaNameList;
	}
	/**
	 * Create a copy of this DbEnumType object with a new value for field {@link #valueAndJavaNameList}.<br>
	 * @param valueAndJavaNameList The new value for field {@link #valueAndJavaNameList}
	 * @return A new instance of {@link DbEnumType}
	 */
	@Generated
	public  DbEnumType	withValueAndJavaNameList(@Nullable PList<Tuple2<String,String>> valueAndJavaNameList){
		return new DbEnumType(schema, name, valueAndJavaNameList, javaPackageName, javaClassName);
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
	 * Create a copy of this DbEnumType object with a new value for field {@link #javaPackageName}.<br>
	 * @param javaPackageName The new value for field {@link #javaPackageName}
	 * @return A new instance of {@link DbEnumType}
	 */
	@Generated
	public  DbEnumType	withJavaPackageName(String javaPackageName){
		return new DbEnumType(schema, name, valueAndJavaNameList, javaPackageName, javaClassName);
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
	 * Create a copy of this DbEnumType object with a new value for field {@link #javaClassName}.<br>
	 * @param javaClassName The new value for field {@link #javaClassName}
	 * @return A new instance of {@link DbEnumType}
	 */
	@Generated
	public  DbEnumType	withJavaClassName(String javaClassName){
		return new DbEnumType(schema, name, valueAndJavaNameList, javaPackageName, javaClassName);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbEnumType == false) return false;
		DbEnumType obj = (DbEnumType)o;
		if(!schema.equals(obj.schema)) return false;
		if(!name.equals(obj.name)) return false;
		if(!valueAndJavaNameList.equals(obj.valueAndJavaNameList)) return false;
		if(!javaPackageName.equals(obj.javaPackageName)) return false;
		if(!javaClassName.equals(obj.javaClassName)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.schema != null ? this.schema.hashCode() : 0);
		result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
		result = 31 * result + (this.valueAndJavaNameList != null ? this.valueAndJavaNameList.hashCode() : 0);
		result = 31 * result + (this.javaPackageName != null ? this.javaPackageName.hashCode() : 0);
		result = 31 * result + (this.javaClassName != null ? this.javaClassName.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbEnumType[" + 
			"schema=" + schema + 
			", name=" + (name == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(name),32,"...") + '\"') +
			", valueAndJavaNameList=" + valueAndJavaNameList + 
			", javaPackageName=" + (javaPackageName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(javaPackageName),32,"...") + '\"') +
			", javaClassName=" + (javaClassName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(javaClassName),32,"...") + '\"') +
			']';
	}
}
