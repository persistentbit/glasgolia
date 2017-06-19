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
public class DbMetaTable extends BaseValueClass{
	private final String       type;
	private final DbMetaSchema schema;

	private final String              name;
	private final PList<DbMetaColumn> columns;
	private final PList<DbMetaColumn> primKey;
	@Nullable
	private final String              comment;


	public DbMetaTable(String type, DbMetaSchema schema, String name,
					   PList<DbMetaColumn> columns,
					   PList<DbMetaColumn> primKey,
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
	public DbMetaTable(String type, DbMetaSchema schema, String name){
		this(type,schema,name,PList.empty(),PList.empty(),null);
	}



	public DbMetaTable add(DbMetaColumn col){
		return copyWith("columns",columns.plus(col));
	}
	public DbMetaColumn column(String name){
		return columns.find(c -> c.getName().equals(name)).get();
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public PList<DbMetaColumn> getColumns() {
		return columns;
	}

	public PList<DbMetaColumn> getPrimKey() {
		return primKey;
	}

	public Result<String> getComment() {
		return Result.result(comment);
	}
	public DbMetaTable comment(String comment){
		return copyWith("comment",comment);
	}
}
