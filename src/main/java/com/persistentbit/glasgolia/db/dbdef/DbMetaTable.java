package com.persistentbit.glasgolia.db.dbdef;

import com.persistentbit.core.Nullable;
import com.persistentbit.core.collections.PList;
import com.persistentbit.core.javacodegen.annotations.NoWith;
import com.persistentbit.core.javacodegen.annotations.CaseClass;
import com.persistentbit.core.result.Result;
import com.persistentbit.core.utils.builders.NOT;
import java.util.Optional;
import java.lang.SuppressWarnings;
import com.persistentbit.core.function.ThrowingFunction;
import com.persistentbit.core.utils.UString;
import com.persistentbit.core.utils.BaseValueClass;
import com.persistentbit.core.javacodegen.annotations.NoGet;
import java.util.Objects;
import com.persistentbit.core.utils.builders.SET;
import com.persistentbit.core.javacodegen.annotations.Generated;
import java.util.function.Function;
import com.persistentbit.core.javacodegen.annotations.DefaultEmpty;

/**
 * TODOC
 * 
 * @author petermuys
 * @since 3/06/17
 */
@CaseClass
public class DbMetaTable extends BaseValueClass {
	public  final	String	type;
	public  final	DbMetaSchema	schema;
	public  final	String	name;
	@DefaultEmpty
	public  final	PList<DbMetaColumn>	columns;
	@DefaultEmpty
	public  final	PList<DbMetaColumn>	primKey;
	@Nullable
	public  final	String	comment;
	
	
	@Generated
	public DbMetaTable(String type, DbMetaSchema schema, String name, @Nullable PList<DbMetaColumn> columns, @Nullable PList<DbMetaColumn> primKey, @Nullable String comment){
			this.type = Objects.requireNonNull(type, "type can not be null");
			this.schema = Objects.requireNonNull(schema, "schema can not be null");
			this.name = Objects.requireNonNull(name, "name can not be null");
			this.columns = columns == null ? PList.empty() : columns;
			this.primKey = primKey == null ? PList.empty() : primKey;
			this.comment = comment;
	}
	@Generated
	public DbMetaTable(String type, DbMetaSchema schema, String name){
			this(type, schema, name, null, null, null);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3> {
		private	String	type;
		private	DbMetaSchema	schema;
		private	String	name;
		private	PList<DbMetaColumn>	columns;
		private	PList<DbMetaColumn>	primKey;
		private	String	comment;
		
		
		public  Builder<SET, _T2, _T3>	setType(String type){
			this.type	=	type;
			return (Builder<SET, _T2, _T3>)this;
		}
		public  Builder<_T1, SET, _T3>	setSchema(DbMetaSchema schema){
			this.schema	=	schema;
			return (Builder<_T1, SET, _T3>)this;
		}
		public  Builder<_T1, _T2, SET>	setName(String name){
			this.name	=	name;
			return (Builder<_T1, _T2, SET>)this;
		}
		public  Builder<_T1, _T2, _T3>	setColumns(@Nullable PList<DbMetaColumn> columns){
			this.columns	=	columns;
			return this;
		}
		public  Builder<_T1, _T2, _T3>	setPrimKey(@Nullable PList<DbMetaColumn> primKey){
			this.primKey	=	primKey;
			return this;
		}
		public  Builder<_T1, _T2, _T3>	setComment(@Nullable String comment){
			this.comment	=	comment;
			return this;
		}
	}
	/**
	 * Get the value of field {@link #type}.<br>
	 * @return {@link #type}
	 */
	@Generated
	public  String	getType(){
		return this.type;
	}
	/**
	 * Create a copy of this DbMetaTable object with a new value for field {@link #type}.<br>
	 * @param type The new value for field {@link #type}
	 * @return A new instance of {@link DbMetaTable}
	 */
	@Generated
	public  DbMetaTable	withType(String type){
		return new DbMetaTable(type, schema, name, columns, primKey, comment);
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
	 * Create a copy of this DbMetaTable object with a new value for field {@link #schema}.<br>
	 * @param schema The new value for field {@link #schema}
	 * @return A new instance of {@link DbMetaTable}
	 */
	@Generated
	public  DbMetaTable	withSchema(DbMetaSchema schema){
		return new DbMetaTable(type, schema, name, columns, primKey, comment);
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
	 * Create a copy of this DbMetaTable object with a new value for field {@link #name}.<br>
	 * @param name The new value for field {@link #name}
	 * @return A new instance of {@link DbMetaTable}
	 */
	@Generated
	public  DbMetaTable	withName(String name){
		return new DbMetaTable(type, schema, name, columns, primKey, comment);
	}
	/**
	 * Get the value of field {@link #columns}.<br>
	 * @return {@link #columns}
	 */
	@Generated
	public  PList<DbMetaColumn>	getColumns(){
		return this.columns;
	}
	/**
	 * Create a copy of this DbMetaTable object with a new value for field {@link #columns}.<br>
	 * @param columns The new value for field {@link #columns}
	 * @return A new instance of {@link DbMetaTable}
	 */
	@Generated
	public  DbMetaTable	withColumns(@Nullable PList<DbMetaColumn> columns){
		return new DbMetaTable(type, schema, name, columns, primKey, comment);
	}
	/**
	 * Get the value of field {@link #primKey}.<br>
	 * @return {@link #primKey}
	 */
	@Generated
	public  PList<DbMetaColumn>	getPrimKey(){
		return this.primKey;
	}
	/**
	 * Create a copy of this DbMetaTable object with a new value for field {@link #primKey}.<br>
	 * @param primKey The new value for field {@link #primKey}
	 * @return A new instance of {@link DbMetaTable}
	 */
	@Generated
	public  DbMetaTable	withPrimKey(@Nullable PList<DbMetaColumn> primKey){
		return new DbMetaTable(type, schema, name, columns, primKey, comment);
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
	 * Create a copy of this DbMetaTable object with a new value for field {@link #comment}.<br>
	 * @param comment The new value for field {@link #comment}
	 * @return A new instance of {@link DbMetaTable}
	 */
	@Generated
	public  DbMetaTable	withComment(@Nullable String comment){
		return new DbMetaTable(type, schema, name, columns, primKey, comment);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbMetaTable == false) return false;
		DbMetaTable obj = (DbMetaTable)o;
		if(!type.equals(obj.type)) return false;
		if(!schema.equals(obj.schema)) return false;
		if(!name.equals(obj.name)) return false;
		if(!columns.equals(obj.columns)) return false;
		if(!primKey.equals(obj.primKey)) return false;
		if(comment != null ? !comment.equals(obj.comment) : obj.comment!= null) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.type != null ? this.type.hashCode() : 0);
		result = 31 * result + (this.schema != null ? this.schema.hashCode() : 0);
		result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
		result = 31 * result + (this.columns != null ? this.columns.hashCode() : 0);
		result = 31 * result + (this.primKey != null ? this.primKey.hashCode() : 0);
		result = 31 * result + (this.comment != null ? this.comment.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbMetaTable[" + 
			"type=" + (type == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(type),32,"...") + '\"') +
			", schema=" + schema + 
			", name=" + (name == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(name),32,"...") + '\"') +
			", columns=" + columns + 
			", primKey=" + primKey + 
			", comment=" + (comment == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(comment),32,"...") + '\"') +
			']';
	}
	@Generated
	public  DbMetaTable	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setType(this.type);
		b.setSchema(this.schema);
		b.setName(this.name);
		b.setColumns(this.columns);
		b.setPrimKey(this.primKey);
		b.setComment(this.comment);
		b = updater.apply(b);
		return new DbMetaTable(b.type, b.schema, b.name, b.columns, b.primKey, b.comment);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static DbMetaTable	build(ThrowingFunction<Builder<NOT,NOT,NOT>, Builder<SET,SET,SET>, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new DbMetaTable(b.type, b.schema, b.name, b.columns, b.primKey, b.comment);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Result<DbMetaTable>	buildExc(ThrowingFunction<Builder<NOT,NOT,NOT>, Builder<SET,SET,SET>,Exception> setter){
		return setter.applyResult(new Builder<>()).mapExc(b -> new DbMetaTable(b.type, b.schema, b.name, b.columns, b.primKey, b.comment));
	}
}
