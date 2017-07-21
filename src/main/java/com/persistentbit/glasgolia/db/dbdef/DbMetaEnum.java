package com.persistentbit.glasgolia.db.dbdef;

import com.persistentbit.core.Nullable;
import com.persistentbit.core.collections.PList;
import com.persistentbit.core.javacodegen.annotations.CaseClass;
import com.persistentbit.core.utils.UString;
import com.persistentbit.core.javacodegen.annotations.NoBuilder;
import java.util.Objects;
import com.persistentbit.core.javacodegen.annotations.DefaultValue;
import com.persistentbit.core.javacodegen.annotations.Generated;

/**
 * TODOC
 * 
 * @author petermuys
 * @since 16/07/17
 */
@CaseClass
@NoBuilder
public class DbMetaEnum {
	private  final	DbMetaSchema	schema;
	private  final	String	name;
	@DefaultValue("PList.empty()")
	private  final	PList<String>	values;
	
	
	@Generated
	public DbMetaEnum(DbMetaSchema schema, String name, @Nullable PList<String> values){
			this.schema = Objects.requireNonNull(schema, "schema can not be null");
			this.name = Objects.requireNonNull(name, "name can not be null");
			this.values = values == null ? PList.empty() : values;
	}
	@Generated
	public DbMetaEnum(DbMetaSchema schema, String name){
			this(schema, name, null);
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
	 * Create a copy of this DbMetaEnum object with a new value for field {@link #schema}.<br>
	 * @param schema The new value for field {@link #schema}
	 * @return A new instance of {@link DbMetaEnum}
	 */
	@Generated
	public  DbMetaEnum	withSchema(DbMetaSchema schema){
		return new DbMetaEnum(schema, name, values);
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
	 * Create a copy of this DbMetaEnum object with a new value for field {@link #name}.<br>
	 * @param name The new value for field {@link #name}
	 * @return A new instance of {@link DbMetaEnum}
	 */
	@Generated
	public  DbMetaEnum	withName(String name){
		return new DbMetaEnum(schema, name, values);
	}
	/**
	 * Get the value of field {@link #values}.<br>
	 * @return {@link #values}
	 */
	@Generated
	public  PList<String>	getValues(){
		return this.values;
	}
	/**
	 * Create a copy of this DbMetaEnum object with a new value for field {@link #values}.<br>
	 * @param values The new value for field {@link #values}
	 * @return A new instance of {@link DbMetaEnum}
	 */
	@Generated
	public  DbMetaEnum	withValues(@Nullable PList<String> values){
		return new DbMetaEnum(schema, name, values);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbMetaEnum == false) return false;
		DbMetaEnum obj = (DbMetaEnum)o;
		if(!schema.equals(obj.schema)) return false;
		if(!name.equals(obj.name)) return false;
		if(!values.equals(obj.values)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.schema != null ? this.schema.hashCode() : 0);
		result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
		result = 31 * result + (this.values != null ? this.values.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbMetaEnum[" + 
			"schema=" + schema + 
			", name=" + (name == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(name),32,"...") + '\"') +
			", values=" + values + 
			']';
	}
}
