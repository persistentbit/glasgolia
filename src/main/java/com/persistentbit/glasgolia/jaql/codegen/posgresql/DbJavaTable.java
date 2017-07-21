package com.persistentbit.glasgolia.jaql.codegen.posgresql;

import com.persistentbit.core.Nullable;
import com.persistentbit.core.collections.PList;
import com.persistentbit.core.javacodegen.annotations.CaseClass;
import com.persistentbit.glasgolia.db.dbdef.DbMetaTable;
import com.persistentbit.core.utils.UString;
import com.persistentbit.core.javacodegen.annotations.NoBuilder;
import java.util.Objects;
import com.persistentbit.core.javacodegen.annotations.Generated;

/**
 * TODOC
 * 
 * @author petermuys
 * @since 15/07/17
 */
@CaseClass
@NoBuilder
public class DbJavaTable {
	private  final	DbMetaTable	table;
	private  final	PList<DbJavaField>	javaFields;
	private  final	String	javaClassName;
	private  final	String	packName;
	
	
	@Generated
	public DbJavaTable(DbMetaTable table, PList<DbJavaField> javaFields, String javaClassName, String packName){
			this.table = Objects.requireNonNull(table, "table can not be null");
			this.javaFields = Objects.requireNonNull(javaFields, "javaFields can not be null");
			this.javaClassName = Objects.requireNonNull(javaClassName, "javaClassName can not be null");
			this.packName = Objects.requireNonNull(packName, "packName can not be null");
	}
	/**
	 * Get the value of field {@link #table}.<br>
	 * @return {@link #table}
	 */
	@Generated
	public  DbMetaTable	getTable(){
		return this.table;
	}
	/**
	 * Create a copy of this DbJavaTable object with a new value for field {@link #table}.<br>
	 * @param table The new value for field {@link #table}
	 * @return A new instance of {@link DbJavaTable}
	 */
	@Generated
	public  DbJavaTable	withTable(DbMetaTable table){
		return new DbJavaTable(table, javaFields, javaClassName, packName);
	}
	/**
	 * Get the value of field {@link #javaFields}.<br>
	 * @return {@link #javaFields}
	 */
	@Generated
	public  PList<DbJavaField>	getJavaFields(){
		return this.javaFields;
	}
	/**
	 * Create a copy of this DbJavaTable object with a new value for field {@link #javaFields}.<br>
	 * @param javaFields The new value for field {@link #javaFields}
	 * @return A new instance of {@link DbJavaTable}
	 */
	@Generated
	public  DbJavaTable	withJavaFields(PList<DbJavaField> javaFields){
		return new DbJavaTable(table, javaFields, javaClassName, packName);
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
	 * Create a copy of this DbJavaTable object with a new value for field {@link #javaClassName}.<br>
	 * @param javaClassName The new value for field {@link #javaClassName}
	 * @return A new instance of {@link DbJavaTable}
	 */
	@Generated
	public  DbJavaTable	withJavaClassName(String javaClassName){
		return new DbJavaTable(table, javaFields, javaClassName, packName);
	}
	/**
	 * Get the value of field {@link #packName}.<br>
	 * @return {@link #packName}
	 */
	@Generated
	public  String	getPackName(){
		return this.packName;
	}
	/**
	 * Create a copy of this DbJavaTable object with a new value for field {@link #packName}.<br>
	 * @param packName The new value for field {@link #packName}
	 * @return A new instance of {@link DbJavaTable}
	 */
	@Generated
	public  DbJavaTable	withPackName(String packName){
		return new DbJavaTable(table, javaFields, javaClassName, packName);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbJavaTable == false) return false;
		DbJavaTable obj = (DbJavaTable)o;
		if(!table.equals(obj.table)) return false;
		if(!javaFields.equals(obj.javaFields)) return false;
		if(!javaClassName.equals(obj.javaClassName)) return false;
		if(!packName.equals(obj.packName)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.table != null ? this.table.hashCode() : 0);
		result = 31 * result + (this.javaFields != null ? this.javaFields.hashCode() : 0);
		result = 31 * result + (this.javaClassName != null ? this.javaClassName.hashCode() : 0);
		result = 31 * result + (this.packName != null ? this.packName.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbJavaTable[" + 
			"table=" + table + 
			", javaFields=" + javaFields + 
			", javaClassName=" + (javaClassName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(javaClassName),32,"...") + '\"') +
			", packName=" + (packName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(packName),32,"...") + '\"') +
			']';
	}
}
