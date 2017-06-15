package com.persistentbit.glasgolia.db.dbdef;

import com.persistentbit.core.Nullable;
import com.persistentbit.core.javacodegen.annotations.CaseClass;
import com.persistentbit.core.javacodegen.annotations.Generated;
import com.persistentbit.core.utils.UString;
import com.persistentbit.core.utils.builders.NOT;
import com.persistentbit.core.utils.builders.SET;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * TODOC
 * 
 * @author petermuys
 * @since 3/06/17
 */
@CaseClass
public class DbColumn {
	private  final String         name;
	private  final DbMetaDataType type;
	@Nullable
	private  final String         comment;
	@Nullable
	private  final String         defaultValue;
	
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2> {
		private String         name;
		private DbMetaDataType type;
		private String         comment;
		private String         defaultValue;
		
		
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
	
	@Generated
	public DbColumn(String name, DbMetaDataType type, @Nullable String comment, @Nullable String defaultValue){
			this.name = Objects.requireNonNull(name, "name can not be null");
			this.type = Objects.requireNonNull(type, "type can not be null");
			this.comment = comment;
			this.defaultValue = defaultValue;
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
	 * Create a copy of this DbColumn object with a new value for field {@link #name}.<br>
	 * @param name The new value for field {@link #name}
	 * @return A new instance of {@link DbColumn}
	 */
	@Generated
	public  DbColumn	withName(String name){
		return new DbColumn(name, type, comment, defaultValue);
	}
	/**
	 * Get the value of field {@link #type}.<br>
	 * @return {@link #type}
	 */
	@Generated
	public DbMetaDataType getType(){
		return this.type;
	}
	/**
	 * Create a copy of this DbColumn object with a new value for field {@link #type}.<br>
	 * @param type The new value for field {@link #type}
	 * @return A new instance of {@link DbColumn}
	 */
	@Generated
	public  DbColumn	withType(DbMetaDataType type){
		return new DbColumn(name, type, comment, defaultValue);
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
	 * Create a copy of this DbColumn object with a new value for field {@link #comment}.<br>
	 * @param comment The new value for field {@link #comment}
	 * @return A new instance of {@link DbColumn}
	 */
	@Generated
	public  DbColumn	withComment(@Nullable String comment){
		return new DbColumn(name, type, comment, defaultValue);
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
	 * Create a copy of this DbColumn object with a new value for field {@link #defaultValue}.<br>
	 * @param defaultValue The new value for field {@link #defaultValue}
	 * @return A new instance of {@link DbColumn}
	 */
	@Generated
	public  DbColumn	withDefaultValue(@Nullable String defaultValue){
		return new DbColumn(name, type, comment, defaultValue);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbColumn == false) return false;
		DbColumn obj = (DbColumn)o;
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
		return "DbColumn[" + 
			"name=" + (name == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(name),32,"...") + '\"') +
			", type=" + type + 
			", comment=" + (comment == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(comment),32,"...") + '\"') +
			", defaultValue=" + (defaultValue == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(defaultValue),32,"...") + '\"') +
			']';
	}
	@Generated
	public  DbColumn	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setName(this.name);
		b.setType(this.type);
		b.setComment(this.comment);
		b.setDefaultValue(this.defaultValue);
		b = updater.apply(b);
		return new DbColumn(b.name, b.type, b.comment, b.defaultValue);
	}
	@Generated
	public  static DbColumn	build(Function<Builder<NOT,NOT>, Builder<SET, SET>> setter){
		Builder b = setter.apply(new Builder());
		return new DbColumn(b.name, b.type, b.comment, b.defaultValue);
	}
}
