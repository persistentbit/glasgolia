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
 * @since 3/06/17
 */
@CaseClass
public class DbMetaColumn {
	public  final	String	name;
	public  final	DbMetaDataType	type;
	@Nullable
	public  final	String	comment;
	@Nullable
	public  final	String	defaultValue;
	
	
	@Generated
	public DbMetaColumn(String name, DbMetaDataType type, @Nullable String comment, @Nullable String defaultValue){
			this.name = Objects.requireNonNull(name, "name can not be null");
			this.type = Objects.requireNonNull(type, "type can not be null");
			this.comment = comment;
			this.defaultValue = defaultValue;
	}
	@Generated
	public DbMetaColumn(String name, DbMetaDataType type){
			this(name, type, null, null);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2> {
		private	String	name;
		private	DbMetaDataType	type;
		private	String	comment;
		private	String	defaultValue;
		
		
		public  Builder<SET, _T2>	setName(String name){
			this.name	=	name;
			return (Builder<SET, _T2>)this;
		}
		public  Builder<_T1, SET>	setType(DbMetaDataType type){
			this.type	=	type;
			return (Builder<_T1, SET>)this;
		}
		public  Builder<_T1, _T2>	setComment(@Nullable String comment){
			this.comment	=	comment;
			return this;
		}
		public  Builder<_T1, _T2>	setDefaultValue(@Nullable String defaultValue){
			this.defaultValue	=	defaultValue;
			return this;
		}
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
	 * Create a copy of this DbMetaColumn object with a new value for field {@link #name}.<br>
	 * @param name The new value for field {@link #name}
	 * @return A new instance of {@link DbMetaColumn}
	 */
	@Generated
	public  DbMetaColumn	withName(String name){
		return new DbMetaColumn(name, type, comment, defaultValue);
	}
	/**
	 * Get the value of field {@link #type}.<br>
	 * @return {@link #type}
	 */
	@Generated
	public  DbMetaDataType	getType(){
		return this.type;
	}
	/**
	 * Create a copy of this DbMetaColumn object with a new value for field {@link #type}.<br>
	 * @param type The new value for field {@link #type}
	 * @return A new instance of {@link DbMetaColumn}
	 */
	@Generated
	public  DbMetaColumn	withType(DbMetaDataType type){
		return new DbMetaColumn(name, type, comment, defaultValue);
	}
	/**
	 * Get the value of field {@link #comment}.<br>
	 * @return {@link #comment}
	 */
	@Generated
	public  Optional<String>	getComment(){
		return Optional.ofNullable(this.comment);
	}
	/**
	 * Create a copy of this DbMetaColumn object with a new value for field {@link #comment}.<br>
	 * @param comment The new value for field {@link #comment}
	 * @return A new instance of {@link DbMetaColumn}
	 */
	@Generated
	public  DbMetaColumn	withComment(@Nullable String comment){
		return new DbMetaColumn(name, type, comment, defaultValue);
	}
	/**
	 * Get the value of field {@link #defaultValue}.<br>
	 * @return {@link #defaultValue}
	 */
	@Generated
	public  Optional<String>	getDefaultValue(){
		return Optional.ofNullable(this.defaultValue);
	}
	/**
	 * Create a copy of this DbMetaColumn object with a new value for field {@link #defaultValue}.<br>
	 * @param defaultValue The new value for field {@link #defaultValue}
	 * @return A new instance of {@link DbMetaColumn}
	 */
	@Generated
	public  DbMetaColumn	withDefaultValue(@Nullable String defaultValue){
		return new DbMetaColumn(name, type, comment, defaultValue);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbMetaColumn == false) return false;
		DbMetaColumn obj = (DbMetaColumn)o;
		if(!name.equals(obj.name)) return false;
		if(!type.equals(obj.type)) return false;
		if(comment != null ? !comment.equals(obj.comment) : obj.comment!= null) return false;
		if(defaultValue != null ? !defaultValue.equals(obj.defaultValue) : obj.defaultValue!= null) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.name != null ? this.name.hashCode() : 0);
		result = 31 * result + (this.type != null ? this.type.hashCode() : 0);
		result = 31 * result + (this.comment != null ? this.comment.hashCode() : 0);
		result = 31 * result + (this.defaultValue != null ? this.defaultValue.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbMetaColumn[" + 
			"name=" + (name == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(name),32,"...") + '\"') +
			", type=" + type + 
			", comment=" + (comment == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(comment),32,"...") + '\"') +
			", defaultValue=" + (defaultValue == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(defaultValue),32,"...") + '\"') +
			']';
	}
	@Generated
	public  DbMetaColumn	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setName(this.name);
		b.setType(this.type);
		b.setComment(this.comment);
		b.setDefaultValue(this.defaultValue);
		b = updater.apply(b);
		return new DbMetaColumn(b.name, b.type, b.comment, b.defaultValue);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static DbMetaColumn	build(ThrowingFunction<Builder<NOT,NOT>, Builder<SET,SET>, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new DbMetaColumn(b.name, b.type, b.comment, b.defaultValue);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Result<DbMetaColumn>	buildExc(ThrowingFunction<Builder<NOT,NOT>, Builder<SET,SET>,Exception> setter){
		return setter.applyResult(new Builder<>()).mapExc(b -> new DbMetaColumn(b.name, b.type, b.comment, b.defaultValue));
	}
}
