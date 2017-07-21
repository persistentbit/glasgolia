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
import com.persistentbit.core.javacodegen.annotations.Generated;
import java.util.function.Function;

/**
 * TODOC
 * 
 * @author petermuys
 * @since 21/07/17
 */
@CaseClass
public class DbMetaUDT {
	private  final	DbMetaSchema	schema;
	private  final	String	name;
	@Nullable
	private  final	String	javaClassName;
	private  final	int	dataType;
	@Nullable
	private  final	String	remarks;
	private  final	int	baseType;
	
	
	@Generated
	public DbMetaUDT(DbMetaSchema schema, String name, @Nullable String javaClassName, int dataType, @Nullable String remarks, int baseType){
			this.schema = Objects.requireNonNull(schema, "schema can not be null");
			this.name = Objects.requireNonNull(name, "name can not be null");
			this.javaClassName = javaClassName;
			this.dataType = Objects.requireNonNull(dataType, "dataType can not be null");
			this.remarks = remarks;
			this.baseType = Objects.requireNonNull(baseType, "baseType can not be null");
	}
	@Generated
	public DbMetaUDT(DbMetaSchema schema, String name, int dataType, int baseType){
			this(schema, name, null, dataType, null, baseType);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3, _T4> {
		private	DbMetaSchema	schema;
		private	String	name;
		private	String	javaClassName;
		private	int	dataType;
		private	String	remarks;
		private	int	baseType;
		
		
		public  Builder<SET, _T2, _T3, _T4>	setSchema(DbMetaSchema schema){
			this.schema	=	schema;
			return (Builder<SET, _T2, _T3, _T4>)this;
		}
		public  Builder<_T1, SET, _T3, _T4>	setName(String name){
			this.name	=	name;
			return (Builder<_T1, SET, _T3, _T4>)this;
		}
		public  Builder<_T1, _T2, _T3, _T4>	setJavaClassName(@Nullable String javaClassName){
			this.javaClassName	=	javaClassName;
			return this;
		}
		public  Builder<_T1, _T2, SET, _T4>	setDataType(int dataType){
			this.dataType	=	dataType;
			return (Builder<_T1, _T2, SET, _T4>)this;
		}
		public  Builder<_T1, _T2, _T3, _T4>	setRemarks(@Nullable String remarks){
			this.remarks	=	remarks;
			return this;
		}
		public  Builder<_T1, _T2, _T3, SET>	setBaseType(int baseType){
			this.baseType	=	baseType;
			return (Builder<_T1, _T2, _T3, SET>)this;
		}
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
	 * Create a copy of this DbMetaUDT object with a new value for field {@link #schema}.<br>
	 * @param schema The new value for field {@link #schema}
	 * @return A new instance of {@link DbMetaUDT}
	 */
	@Generated
	public  DbMetaUDT	withSchema(DbMetaSchema schema){
		return new DbMetaUDT(schema, name, javaClassName, dataType, remarks, baseType);
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
	 * Create a copy of this DbMetaUDT object with a new value for field {@link #name}.<br>
	 * @param name The new value for field {@link #name}
	 * @return A new instance of {@link DbMetaUDT}
	 */
	@Generated
	public  DbMetaUDT	withName(String name){
		return new DbMetaUDT(schema, name, javaClassName, dataType, remarks, baseType);
	}
	/**
	 * Get the value of field {@link #javaClassName}.<br>
	 * @return {@link #javaClassName}
	 */
	@Generated
	public  Optional<String>	getJavaClassName(){
		return Optional.ofNullable(this.javaClassName);
	}
	/**
	 * Create a copy of this DbMetaUDT object with a new value for field {@link #javaClassName}.<br>
	 * @param javaClassName The new value for field {@link #javaClassName}
	 * @return A new instance of {@link DbMetaUDT}
	 */
	@Generated
	public  DbMetaUDT	withJavaClassName(@Nullable String javaClassName){
		return new DbMetaUDT(schema, name, javaClassName, dataType, remarks, baseType);
	}
	/**
	 * Get the value of field {@link #dataType}.<br>
	 * @return {@link #dataType}
	 */
	@Generated
	public  int	getDataType(){
		return this.dataType;
	}
	/**
	 * Create a copy of this DbMetaUDT object with a new value for field {@link #dataType}.<br>
	 * @param dataType The new value for field {@link #dataType}
	 * @return A new instance of {@link DbMetaUDT}
	 */
	@Generated
	public  DbMetaUDT	withDataType(int dataType){
		return new DbMetaUDT(schema, name, javaClassName, dataType, remarks, baseType);
	}
	/**
	 * Get the value of field {@link #remarks}.<br>
	 * @return {@link #remarks}
	 */
	@Generated
	public  Optional<String>	getRemarks(){
		return Optional.ofNullable(this.remarks);
	}
	/**
	 * Create a copy of this DbMetaUDT object with a new value for field {@link #remarks}.<br>
	 * @param remarks The new value for field {@link #remarks}
	 * @return A new instance of {@link DbMetaUDT}
	 */
	@Generated
	public  DbMetaUDT	withRemarks(@Nullable String remarks){
		return new DbMetaUDT(schema, name, javaClassName, dataType, remarks, baseType);
	}
	/**
	 * Get the value of field {@link #baseType}.<br>
	 * @return {@link #baseType}
	 */
	@Generated
	public  int	getBaseType(){
		return this.baseType;
	}
	/**
	 * Create a copy of this DbMetaUDT object with a new value for field {@link #baseType}.<br>
	 * @param baseType The new value for field {@link #baseType}
	 * @return A new instance of {@link DbMetaUDT}
	 */
	@Generated
	public  DbMetaUDT	withBaseType(int baseType){
		return new DbMetaUDT(schema, name, javaClassName, dataType, remarks, baseType);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbMetaUDT == false) return false;
		DbMetaUDT obj = (DbMetaUDT)o;
		if(!schema.equals(obj.schema)) return false;
		if(!name.equals(obj.name)) return false;
		if(javaClassName != null ? !javaClassName.equals(obj.javaClassName) : obj.javaClassName!= null) return false;
		if(dataType!= obj.dataType) return false;
		if(remarks != null ? !remarks.equals(obj.remarks) : obj.remarks!= null) return false;
		if(baseType!= obj.baseType) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.schema != null ? this.schema.hashCode() : 0);
		result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
		result = 31 * result + (this.javaClassName != null ? this.javaClassName.hashCode() : 0);
		result = 31 * result + this.dataType;
		result = 31 * result + (this.remarks != null ? this.remarks.hashCode() : 0);
		result = 31 * result + this.baseType;
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbMetaUDT[" + 
			"schema=" + schema + 
			", name=" + (name == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(name),32,"...") + '\"') +
			", javaClassName=" + (javaClassName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(javaClassName),32,"...") + '\"') +
			", dataType=" + dataType + 
			", remarks=" + (remarks == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(remarks),32,"...") + '\"') +
			", baseType=" + baseType + 
			']';
	}
	@Generated
	public  DbMetaUDT	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setSchema(this.schema);
		b.setName(this.name);
		b.setJavaClassName(this.javaClassName);
		b.setDataType(this.dataType);
		b.setRemarks(this.remarks);
		b.setBaseType(this.baseType);
		b = updater.apply(b);
		return new DbMetaUDT(b.schema, b.name, b.javaClassName, b.dataType, b.remarks, b.baseType);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static DbMetaUDT	build(ThrowingFunction<Builder<NOT,NOT,NOT,NOT>, Builder<SET,SET,SET,SET>, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new DbMetaUDT(b.schema, b.name, b.javaClassName, b.dataType, b.remarks, b.baseType);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Result<DbMetaUDT>	buildExc(ThrowingFunction<Builder<NOT,NOT,NOT,NOT>, Builder<SET,SET,SET,SET>,Exception> setter){
		return setter.applyResult(new Builder<>()).mapExc(b -> new DbMetaUDT(b.schema, b.name, b.javaClassName, b.dataType, b.remarks, b.baseType));
	}
}
