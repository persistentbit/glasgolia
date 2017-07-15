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
	private  final	String	fullJavaName;
	
	
	@Generated
	public DbJavaTable(DbMetaTable table, PList<DbJavaField> javaFields, String fullJavaName){
			this.table = Objects.requireNonNull(table, "table can not be null");
			this.javaFields = Objects.requireNonNull(javaFields, "javaFields can not be null");
			this.fullJavaName = Objects.requireNonNull(fullJavaName, "fullJavaName can not be null");
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
		return new DbJavaTable(table, javaFields, fullJavaName);
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
		return new DbJavaTable(table, javaFields, fullJavaName);
	}
	/**
	 * Get the value of field {@link #fullJavaName}.<br>
	 * @return {@link #fullJavaName}
	 */
	@Generated
	public  String	getFullJavaName(){
		return this.fullJavaName;
	}
	/**
	 * Create a copy of this DbJavaTable object with a new value for field {@link #fullJavaName}.<br>
	 * @param fullJavaName The new value for field {@link #fullJavaName}
	 * @return A new instance of {@link DbJavaTable}
	 */
	@Generated
	public  DbJavaTable	withFullJavaName(String fullJavaName){
		return new DbJavaTable(table, javaFields, fullJavaName);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbJavaTable == false) return false;
		DbJavaTable obj = (DbJavaTable)o;
		if(!table.equals(obj.table)) return false;
		if(!javaFields.equals(obj.javaFields)) return false;
		if(!fullJavaName.equals(obj.fullJavaName)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.table != null ? this.table.hashCode() : 0);
		result = 31 * result + (this.javaFields != null ? this.javaFields.hashCode() : 0);
		result = 31 * result + (this.fullJavaName != null ? this.fullJavaName.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbJavaTable[" + 
			"table=" + table + 
			", javaFields=" + javaFields + 
			", fullJavaName=" + (fullJavaName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(fullJavaName),32,"...") + '\"') +
			']';
	}
}
