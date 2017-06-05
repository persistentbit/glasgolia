package com.persistentbit.glasgolia.nativesql;

import com.persistentbit.core.collections.PMap;
import com.persistentbit.core.utils.BaseValueClass;

/**
 * TODOC
 *
 * @author petermuys
 * @since 5/06/17
 */
public class SqlSelect extends BaseValueClass{
	private final String sql;
	private final PMap<String,Object> params;

	public SqlSelect(String sql, PMap<String, Object> params) {
		this.sql = sql;
		this.params = params;
		checkNullFields();
	}
	public SqlSelect(String sql){
		this(sql,PMap.empty());
	}
	public SqlSelect set(String paramName, Object value){
		return copyWith("params",params.put(paramName,value));
	}
/*
	public <T> Result<PList<T>> selectList(Function<ResultSet,T> rowMapper){

	}

	public <T> Result<PList<T>> selectZeroOrOne(Function<ResultSet,T> rowMapper){

	}
	private Result<ResultSet> execute(Connection con){
		return Result.function().code(l -> {



			try(PreparedStatement stat = con.prepareStatement(jdbcSql)){
				stat.set
			}
		});
	}*/
}
