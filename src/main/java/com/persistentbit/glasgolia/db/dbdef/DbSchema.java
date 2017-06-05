package com.persistentbit.glasgolia.db.dbdef;

import com.persistentbit.core.Nullable;
import com.persistentbit.core.result.Result;
import com.persistentbit.core.utils.BaseValueClass;

/**
 * TODOC
 *
 * @author petermuys
 * @since 3/06/17
 */
public class DbSchema extends BaseValueClass{
	private final DbCatalog catalog;
	@Nullable
	private final String name;
	@Nullable
	private final String comment;

	public DbSchema(DbCatalog catalog,@Nullable  String name,@Nullable String comment
	) {
		this.catalog = catalog;
		this.name = name;
		this.comment = comment;
		checkNullFields();
	}
	public DbSchema(DbCatalog catalog,String name){
		this(catalog,name,null);
	}
	public DbSchema() {
		this(null,null);
	}

	public DbSchema comment(String comment){
		return copyWith("comment",comment);
	}

	public Result<String> getName() {
		return Result.result(name);
	}

	public DbCatalog getCatalog() { return catalog;}

	public Result<String> getComment() {
		return Result.result(comment);
	}

	public String getFullName(){
		String res = catalog.getName().map(name -> name + ".").orElse("");
		return res + (name == null ? "" : name);
	}

	@Override
	public String toString() {
		return "DbSchema[" + getFullName() + "]";
	}
}
