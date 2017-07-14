package com.persistentbit.glasgolia.jaql.codegen.posgresql;

import com.persistentbit.core.Nullable;
import com.persistentbit.core.collections.PList;
import com.persistentbit.core.function.ThrowingFunction;
import com.persistentbit.core.javacodegen.annotations.CaseClass;
import com.persistentbit.core.javacodegen.annotations.DefaultValue;
import com.persistentbit.core.javacodegen.annotations.Generated;
import com.persistentbit.core.javacodegen.annotations.NoWith;
import com.persistentbit.core.printing.PrintableText;
import com.persistentbit.core.result.Result;
import com.persistentbit.core.utils.builders.NOT;
import com.persistentbit.core.utils.builders.SET;
import com.persistentbit.glasgolia.db.dbdef.DbMetaCatalog;
import com.persistentbit.glasgolia.db.dbdef.DbMetaDataImporter;
import com.persistentbit.glasgolia.db.dbdef.DbMetaSchema;
import com.persistentbit.glasgolia.db.dbdef.DbMetaTable;
import com.persistentbit.glasgolia.db.work.DbRun;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * TODOC
 * 
 * @author petermuys
 * @since 11/07/17
 */
@CaseClass
@NoWith
public class JavaGenTableSelection {
	private  final	DbRun	run;
	@DefaultValue("PList.empty()")
	private  final	PList<DbMetaCatalog>	catalogs;
	@DefaultValue("PList.empty()")
	private  final	PList<DbMetaSchema>	schemas;
	@DefaultValue("PList.empty()")
	private  final	PList<DbMetaTable>	tablesAndViews;
	
	
	@Generated
	public JavaGenTableSelection(DbRun run, @Nullable PList<DbMetaCatalog> catalogs, @Nullable PList<DbMetaSchema> schemas, @Nullable PList<DbMetaTable> tablesAndViews){
			this.run = Objects.requireNonNull(run, "run can not be null");
			this.catalogs = catalogs == null ? PList.empty() : catalogs;
			this.schemas = schemas == null ? PList.empty() : schemas;
			this.tablesAndViews = tablesAndViews == null ? PList.empty() : tablesAndViews;
	}
	@Generated
	public JavaGenTableSelection(DbRun run){
			this(run, null, null, null);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1> {
		private	DbRun	run;
		private	PList<DbMetaCatalog>	catalogs;
		private	PList<DbMetaSchema>	schemas;
		private	PList<DbMetaTable>	tablesAndViews;
		
		
		public  Builder<SET>	setRun(DbRun run){
			this.run	=	run;
			return (Builder<SET>)this;
		}
		public  Builder<_T1>	setCatalogs(@Nullable PList<DbMetaCatalog> catalogs){
			this.catalogs	=	catalogs;
			return this;
		}
		public  Builder<_T1>	setSchemas(@Nullable PList<DbMetaSchema> schemas){
			this.schemas	=	schemas;
			return this;
		}
		public  Builder<_T1>	setTablesAndViews(@Nullable PList<DbMetaTable> tablesAndViews){
			this.tablesAndViews	=	tablesAndViews;
			return this;
		}
	}
	public  PrintableText	show(){
	    return out -> {
	        catalogs.forEach(cat -> {
	            out.println("Catalog " + cat.getName().orElse("") + ": " + cat);
	            out.indent(sout -> {
	                schemas.filter(schema -> schema.getCatalog().equals(cat)).forEach(schema -> {
	                    sout.println("Schema " + schema.getName().orElse("") + ": " + schema);
	                    sout.indent(tout -> {
	                        tablesAndViews.filter(t -> t.getSchema().equals(schema)).forEach(table -> {
	                            tout.println("Table " + table.getName() + ": " + table);
	                            tout.indent(cout -> {
	                                table.getColumns().forEach(col -> {
	                                    cout.println(col.getName() + ": " + col);
	                                });
	                            });
	                        });
	                    });
	                });
	            });
	        });
	    };
	}
	public  Result<JavaGenTableSelection>	addCatalogs(Predicate<DbMetaCatalog> catalogFilter){
	    return DbMetaDataImporter.getCatalogs().transaction(run).map(catList -> catList.filter(catalogFilter)).map(catList -> updated(b -> b.setCatalogs(catalogs.plusAll(catList))));
	}
	public  Result<JavaGenTableSelection>	addSchemas(Predicate<DbMetaSchema> schemaFilter){
	    Predicate<DbMetaSchema> catFilter = schema -> catalogs.contains(schema.getCatalog());
	    return DbMetaDataImporter.getAllSchemas().transaction(run).map(schemaList -> schemaList.filter(catFilter.and(schemaFilter))).map(schemaList -> updated(b -> b.setSchemas(schemas.plusAll(schemaList))));
	}
	public  Result<JavaGenTableSelection>	addTablesAndViews(Predicate<DbMetaTable> tableFilter){
	    return Result.fromSequence(schemas.map(schema -> DbMetaDataImporter.getTablesAndViews(schema).transaction(run).map(tableList -> tableList.filter(tableFilter)))).map(listStream -> listStream.<DbMetaTable>flatten().plist()).map(tableList -> updated(b -> b.setTablesAndViews(tablesAndViews.plusAll(tableList))));
	}
	/**
	 * Get the value of field {@link #run}.<br>
	 * @return {@link #run}
	 */
	@Generated
	public  DbRun	getRun(){
		return this.run;
	}
	/**
	 * Get the value of field {@link #catalogs}.<br>
	 * @return {@link #catalogs}
	 */
	@Generated
	public  PList<DbMetaCatalog>	getCatalogs(){
		return this.catalogs;
	}
	/**
	 * Get the value of field {@link #schemas}.<br>
	 * @return {@link #schemas}
	 */
	@Generated
	public  PList<DbMetaSchema>	getSchemas(){
		return this.schemas;
	}
	/**
	 * Get the value of field {@link #tablesAndViews}.<br>
	 * @return {@link #tablesAndViews}
	 */
	@Generated
	public  PList<DbMetaTable>	getTablesAndViews(){
		return this.tablesAndViews;
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof JavaGenTableSelection == false) return false;
		JavaGenTableSelection obj = (JavaGenTableSelection)o;
		if(!run.equals(obj.run)) return false;
		if(!catalogs.equals(obj.catalogs)) return false;
		if(!schemas.equals(obj.schemas)) return false;
		if(!tablesAndViews.equals(obj.tablesAndViews)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.run != null ? this.run.hashCode() : 0);
		result = 31 * result + (this.catalogs != null ? this.catalogs.hashCode() : 0);
		result = 31 * result + (this.schemas != null ? this.schemas.hashCode() : 0);
		result = 31 * result + (this.tablesAndViews != null ? this.tablesAndViews.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "JavaGenTableSelection[" + 
			"run=" + run + 
			", catalogs=" + catalogs + 
			", schemas=" + schemas + 
			", tablesAndViews=" + tablesAndViews + 
			']';
	}
	@Generated
	public  JavaGenTableSelection	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setRun(this.run);
		b.setCatalogs(this.catalogs);
		b.setSchemas(this.schemas);
		b.setTablesAndViews(this.tablesAndViews);
		b = updater.apply(b);
		return new JavaGenTableSelection(b.run, b.catalogs, b.schemas, b.tablesAndViews);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static JavaGenTableSelection	build(ThrowingFunction<Builder<NOT>, Builder<SET>, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new JavaGenTableSelection(b.run, b.catalogs, b.schemas, b.tablesAndViews);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Result<JavaGenTableSelection>	buildExc(ThrowingFunction<Builder<NOT>, Builder<SET>,Exception> setter){
		return setter.applyResult(new Builder<>()).mapExc(b -> new JavaGenTableSelection(b.run, b.catalogs, b.schemas, b.tablesAndViews));
	}
}
