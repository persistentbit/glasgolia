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
public class DbColumn extends BaseValueClass{
	private final String     name;
	private final DbDataType type;
	@Nullable
	private final String     comment;
	@Nullable
	private final String     defaultValue;

	public DbColumn(String name, DbDataType type, String comment, String defaultValue) {
		this.name = name;
		this.type = type;
		this.comment = comment;
		this.defaultValue = defaultValue;
		checkNullFields();
	}

	public DbColumn(String name, DbDataType type){
		this(name,type,null,null);
	}

	public Result<String> getComment() {
		return Result.result(comment);
	}

	public DbDataType comment(String comment){
		return copyWith("comment",comment);
	}

	public String getName() {
		return name;
	}

	public DbDataType getType() {
		return type;
	}

	public Result<String> getDefaultValue() {
		return Result.result(defaultValue);
	}

	public DbColumn defaultValue(String defaultValue){
		return copyWith("defaultValue",defaultValue);
	}
}
