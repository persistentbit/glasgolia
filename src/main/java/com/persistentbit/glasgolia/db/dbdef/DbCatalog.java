package com.persistentbit.glasgolia.db.dbdef;

import com.persistentbit.core.Nullable;
import com.persistentbit.core.result.Result;
import com.persistentbit.core.utils.BaseValueClass;

/**
 * TODOC
 *
 * @author petermuys
 * @since 5/06/17
 */
public class DbCatalog extends BaseValueClass{
	@Nullable
	private final String name;

	public DbCatalog(@Nullable  String name) {
		this.name = name;
		checkNullFields();
	}
	public Result<String> getName(){
		return Result.result(name);
	}

	@Override
	public String toString() {
		return "DBCatalog[" + getName().orElse("") + "]";
	}
}
