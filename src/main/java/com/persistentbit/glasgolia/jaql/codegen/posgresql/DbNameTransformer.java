package com.persistentbit.glasgolia.jaql.codegen.posgresql;

import com.persistentbit.core.Nullable;
import com.persistentbit.core.javacodegen.annotations.CaseClass;
import com.persistentbit.glasgolia.db.dbdef.DbMetaColumn;
import com.persistentbit.glasgolia.db.dbdef.DbMetaTable;
import com.persistentbit.core.utils.UString;
import com.persistentbit.core.javacodegen.annotations.NoBuilder;
import java.util.function.BiFunction;
import java.util.Objects;
import com.persistentbit.core.javacodegen.annotations.Generated;
import java.util.function.Function;
import com.persistentbit.glasgolia.db.dbdef.DbMetaCatalog;
import com.persistentbit.glasgolia.db.dbdef.DbMetaSchema;

/**
 * TODOC
 * 
 * @author petermuys
 * @since 11/07/17
 */
@CaseClass
@NoBuilder
public class DbNameTransformer {
	private  final	Function<DbMetaCatalog,String>	catalogNameToJava;
	private  final	Function<DbMetaSchema,String>	schemaNameToJava;
	private  final	Function<DbMetaTable,String>	tableNameToJava;
	private  final	BiFunction<DbMetaTable,DbMetaColumn,String>	columNameToJava;
	private  final	Function<String,String>	customTypeNameToJava;
	
	
	public DbNameTransformer(Function<String,String> generalDbNameToJava){
	    this(cat -> generalDbNameToJava.apply(cat.getName().orElse("catalog")).toLowerCase(), schema -> generalDbNameToJava.apply(schema.getName().orElse("schema")).toLowerCase(), table -> generalDbNameToJava.apply(table.getName()), (tab, col) -> UString.firstLowerCase(generalDbNameToJava.apply(col.getName())), generalDbNameToJava);
	}
	@Generated
	public DbNameTransformer(Function<DbMetaCatalog,String> catalogNameToJava, Function<DbMetaSchema,String> schemaNameToJava, Function<DbMetaTable,String> tableNameToJava, BiFunction<DbMetaTable,DbMetaColumn,String> columNameToJava, Function<String,String> customTypeNameToJava){
			this.catalogNameToJava = Objects.requireNonNull(catalogNameToJava, "catalogNameToJava can not be null");
			this.schemaNameToJava = Objects.requireNonNull(schemaNameToJava, "schemaNameToJava can not be null");
			this.tableNameToJava = Objects.requireNonNull(tableNameToJava, "tableNameToJava can not be null");
			this.columNameToJava = Objects.requireNonNull(columNameToJava, "columNameToJava can not be null");
			this.customTypeNameToJava = Objects.requireNonNull(customTypeNameToJava, "customTypeNameToJava can not be null");
	}
	public  String	toJavaName(DbMetaCatalog catalog){
	    return catalogNameToJava.apply(catalog);
	}
	public  String	toJavaName(DbMetaSchema schema){
	    return schemaNameToJava.apply(schema);
	}
	public  String	toJavaName(DbMetaTable table){
	    return tableNameToJava.apply(table);
	}
	public  String	toJavaName(DbMetaTable table, DbMetaColumn column){
	    return columNameToJava.apply(table, column);
	}
	public  String	toJavaName(String customTypeName){
	    return customTypeNameToJava.apply(customTypeName);
	}
	/**
	 * Get the value of field {@link #catalogNameToJava}.<br>
	 * @return {@link #catalogNameToJava}
	 */
	@Generated
	public  Function<DbMetaCatalog,String>	getCatalogNameToJava(){
		return this.catalogNameToJava;
	}
	/**
	 * Create a copy of this DbNameTransformer object with a new value for field {@link #catalogNameToJava}.<br>
	 * @param catalogNameToJava The new value for field {@link #catalogNameToJava}
	 * @return A new instance of {@link DbNameTransformer}
	 */
	@Generated
	public  DbNameTransformer	withCatalogNameToJava(Function<DbMetaCatalog,String> catalogNameToJava){
		return new DbNameTransformer(catalogNameToJava, schemaNameToJava, tableNameToJava, columNameToJava, customTypeNameToJava);
	}
	/**
	 * Get the value of field {@link #schemaNameToJava}.<br>
	 * @return {@link #schemaNameToJava}
	 */
	@Generated
	public  Function<DbMetaSchema,String>	getSchemaNameToJava(){
		return this.schemaNameToJava;
	}
	/**
	 * Create a copy of this DbNameTransformer object with a new value for field {@link #schemaNameToJava}.<br>
	 * @param schemaNameToJava The new value for field {@link #schemaNameToJava}
	 * @return A new instance of {@link DbNameTransformer}
	 */
	@Generated
	public  DbNameTransformer	withSchemaNameToJava(Function<DbMetaSchema,String> schemaNameToJava){
		return new DbNameTransformer(catalogNameToJava, schemaNameToJava, tableNameToJava, columNameToJava, customTypeNameToJava);
	}
	/**
	 * Get the value of field {@link #tableNameToJava}.<br>
	 * @return {@link #tableNameToJava}
	 */
	@Generated
	public  Function<DbMetaTable,String>	getTableNameToJava(){
		return this.tableNameToJava;
	}
	/**
	 * Create a copy of this DbNameTransformer object with a new value for field {@link #tableNameToJava}.<br>
	 * @param tableNameToJava The new value for field {@link #tableNameToJava}
	 * @return A new instance of {@link DbNameTransformer}
	 */
	@Generated
	public  DbNameTransformer	withTableNameToJava(Function<DbMetaTable,String> tableNameToJava){
		return new DbNameTransformer(catalogNameToJava, schemaNameToJava, tableNameToJava, columNameToJava, customTypeNameToJava);
	}
	/**
	 * Get the value of field {@link #columNameToJava}.<br>
	 * @return {@link #columNameToJava}
	 */
	@Generated
	public  BiFunction<DbMetaTable,DbMetaColumn,String>	getColumNameToJava(){
		return this.columNameToJava;
	}
	/**
	 * Create a copy of this DbNameTransformer object with a new value for field {@link #columNameToJava}.<br>
	 * @param columNameToJava The new value for field {@link #columNameToJava}
	 * @return A new instance of {@link DbNameTransformer}
	 */
	@Generated
	public  DbNameTransformer	withColumNameToJava(BiFunction<DbMetaTable,DbMetaColumn,String> columNameToJava){
		return new DbNameTransformer(catalogNameToJava, schemaNameToJava, tableNameToJava, columNameToJava, customTypeNameToJava);
	}
	/**
	 * Get the value of field {@link #customTypeNameToJava}.<br>
	 * @return {@link #customTypeNameToJava}
	 */
	@Generated
	public  Function<String,String>	getCustomTypeNameToJava(){
		return this.customTypeNameToJava;
	}
	/**
	 * Create a copy of this DbNameTransformer object with a new value for field {@link #customTypeNameToJava}.<br>
	 * @param customTypeNameToJava The new value for field {@link #customTypeNameToJava}
	 * @return A new instance of {@link DbNameTransformer}
	 */
	@Generated
	public  DbNameTransformer	withCustomTypeNameToJava(Function<String,String> customTypeNameToJava){
		return new DbNameTransformer(catalogNameToJava, schemaNameToJava, tableNameToJava, columNameToJava, customTypeNameToJava);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbNameTransformer == false) return false;
		DbNameTransformer obj = (DbNameTransformer)o;
		if(!catalogNameToJava.equals(obj.catalogNameToJava)) return false;
		if(!schemaNameToJava.equals(obj.schemaNameToJava)) return false;
		if(!tableNameToJava.equals(obj.tableNameToJava)) return false;
		if(!columNameToJava.equals(obj.columNameToJava)) return false;
		if(!customTypeNameToJava.equals(obj.customTypeNameToJava)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.catalogNameToJava != null ? this.catalogNameToJava.hashCode() : 0);
		result = 31 * result + (this.schemaNameToJava != null ? this.schemaNameToJava.hashCode() : 0);
		result = 31 * result + (this.tableNameToJava != null ? this.tableNameToJava.hashCode() : 0);
		result = 31 * result + (this.columNameToJava != null ? this.columNameToJava.hashCode() : 0);
		result = 31 * result + (this.customTypeNameToJava != null ? this.customTypeNameToJava.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbNameTransformer[" + 
			"catalogNameToJava=" + catalogNameToJava + 
			", schemaNameToJava=" + schemaNameToJava + 
			", tableNameToJava=" + tableNameToJava + 
			", columNameToJava=" + columNameToJava + 
			", customTypeNameToJava=" + customTypeNameToJava + 
			']';
	}
}
