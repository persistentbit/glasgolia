package com.persistentbit.glasgolia.jaql.codegen.posgresql;

import com.persistentbit.core.Nullable;
import com.persistentbit.core.collections.PList;
import com.persistentbit.core.javacodegen.annotations.NoWith;
import com.persistentbit.core.javacodegen.annotations.CaseClass;
import com.persistentbit.core.result.Result;
import com.persistentbit.core.utils.builders.NOT;
import java.lang.SuppressWarnings;
import com.persistentbit.core.function.ThrowingFunction;
import com.persistentbit.core.utils.UString;
import com.persistentbit.core.javacodegen.annotations.NoGet;
import java.util.Objects;
import com.persistentbit.core.utils.builders.SET;
import com.persistentbit.core.javacodegen.annotations.Generated;
import java.util.function.Function;
import com.persistentbit.glasgolia.db.dbdef.DbMetaSchema;

/**
 * TODOC
 * 
 * @author petermuys
 * @since 15/07/17
 */
@CaseClass
public class DbCustomType {
	private  final	DbMetaSchema	definedInSchema;
	private  final	String	javaClassName;
	private  final	PList<DbJavaField>	fields;
	
	
	@Generated
	public DbCustomType(DbMetaSchema definedInSchema, String javaClassName, PList<DbJavaField> fields){
			this.definedInSchema = Objects.requireNonNull(definedInSchema, "definedInSchema can not be null");
			this.javaClassName = Objects.requireNonNull(javaClassName, "javaClassName can not be null");
			this.fields = Objects.requireNonNull(fields, "fields can not be null");
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3> {
		private	DbMetaSchema	definedInSchema;
		private	String	javaClassName;
		private	PList<DbJavaField>	fields;
		
		
		public  Builder<SET, _T2, _T3>	setDefinedInSchema(DbMetaSchema definedInSchema){
			this.definedInSchema	=	definedInSchema;
			return (Builder<SET, _T2, _T3>)this;
		}
		public  Builder<_T1, SET, _T3>	setJavaClassName(String javaClassName){
			this.javaClassName	=	javaClassName;
			return (Builder<_T1, SET, _T3>)this;
		}
		public  Builder<_T1, _T2, SET>	setFields(PList<DbJavaField> fields){
			this.fields	=	fields;
			return (Builder<_T1, _T2, SET>)this;
		}
	}
	/**
	 * Get the value of field {@link #definedInSchema}.<br>
	 * @return {@link #definedInSchema}
	 */
	@Generated
	public  DbMetaSchema	getDefinedInSchema(){
		return this.definedInSchema;
	}
	/**
	 * Create a copy of this DbCustomType object with a new value for field {@link #definedInSchema}.<br>
	 * @param definedInSchema The new value for field {@link #definedInSchema}
	 * @return A new instance of {@link DbCustomType}
	 */
	@Generated
	public  DbCustomType	withDefinedInSchema(DbMetaSchema definedInSchema){
		return new DbCustomType(definedInSchema, javaClassName, fields);
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
	 * Create a copy of this DbCustomType object with a new value for field {@link #javaClassName}.<br>
	 * @param javaClassName The new value for field {@link #javaClassName}
	 * @return A new instance of {@link DbCustomType}
	 */
	@Generated
	public  DbCustomType	withJavaClassName(String javaClassName){
		return new DbCustomType(definedInSchema, javaClassName, fields);
	}
	/**
	 * Get the value of field {@link #fields}.<br>
	 * @return {@link #fields}
	 */
	@Generated
	public  PList<DbJavaField>	getFields(){
		return this.fields;
	}
	/**
	 * Create a copy of this DbCustomType object with a new value for field {@link #fields}.<br>
	 * @param fields The new value for field {@link #fields}
	 * @return A new instance of {@link DbCustomType}
	 */
	@Generated
	public  DbCustomType	withFields(PList<DbJavaField> fields){
		return new DbCustomType(definedInSchema, javaClassName, fields);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbCustomType == false) return false;
		DbCustomType obj = (DbCustomType)o;
		if(!definedInSchema.equals(obj.definedInSchema)) return false;
		if(!javaClassName.equals(obj.javaClassName)) return false;
		if(!fields.equals(obj.fields)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.definedInSchema != null ? this.definedInSchema.hashCode() : 0);
		result = 31 * result + (this.javaClassName != null ? this.javaClassName.hashCode() : 0);
		result = 31 * result + (this.fields != null ? this.fields.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbCustomType[" + 
			"definedInSchema=" + definedInSchema + 
			", javaClassName=" + (javaClassName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(javaClassName),32,"...") + '\"') +
			", fields=" + fields + 
			']';
	}
	@Generated
	public  DbCustomType	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setDefinedInSchema(this.definedInSchema);
		b.setJavaClassName(this.javaClassName);
		b.setFields(this.fields);
		b = updater.apply(b);
		return new DbCustomType(b.definedInSchema, b.javaClassName, b.fields);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static DbCustomType	build(ThrowingFunction<Builder<NOT,NOT,NOT>, Builder<SET,SET,SET>, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new DbCustomType(b.definedInSchema, b.javaClassName, b.fields);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Result<DbCustomType>	buildExc(ThrowingFunction<Builder<NOT,NOT,NOT>, Builder<SET,SET,SET>,Exception> setter){
		return setter.applyResult(new Builder<>()).mapExc(b -> new DbCustomType(b.definedInSchema, b.javaClassName, b.fields));
	}
}
