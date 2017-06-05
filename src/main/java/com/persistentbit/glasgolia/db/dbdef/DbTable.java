package com.persistentbit.glasgolia.db.dbdef;

import com.persistentbit.core.Nullable;
import com.persistentbit.core.collections.PList;
import com.persistentbit.core.result.Result;
import com.persistentbit.core.utils.BaseValueClass;

/**
 * TODOC
 *
 * @author petermuys
 * @since 3/06/17
 */
public class DbTable extends BaseValueClass{
	private final String type;
	private final DbSchema schema;

	private final String          name;
	private final PList<DbColumn> columns;
	private final PList<DbColumn> primKey;
	@Nullable
	private final String          comment;


	public DbTable(String type, DbSchema schema, String name,
				   PList<DbColumn> columns,
				   PList<DbColumn> primKey,
				   String comment
	) {
		this.type = type;
		this.schema = schema;
		this.name = name;
		this.columns = columns;
		this.primKey = primKey;
		this.comment = comment;
		checkNullFields();
	}
	public DbTable(String type, DbSchema schema, String name){
		this(type,schema,name,PList.empty(),PList.empty(),null);
	}



	public DbTable add(DbColumn col){
		return copyWith("columns",columns.plus(col));
	}
	public DbColumn column(String name){
		return columns.find(c -> c.getName().equals(name)).get();
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public PList<DbColumn> getColumns() {
		return columns;
	}

	public PList<DbColumn> getPrimKey() {
		return primKey;
	}

	public Result<String> getComment() {
		return Result.result(comment);
	}
	public DbTable comment(String comment){
		return copyWith("comment",comment);
	}
}
