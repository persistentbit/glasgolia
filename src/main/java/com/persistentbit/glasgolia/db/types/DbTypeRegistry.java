package com.persistentbit.glasgolia.db.types;


import com.persistentbit.core.collections.PMap;
import com.persistentbit.core.logging.FunctionLogging;
import com.persistentbit.core.result.Result;
import com.persistentbit.core.utils.BaseValueClass;
import com.persistentbit.core.utils.UReflect;
import com.persistentbit.glasgolia.db.connections.DbConnector;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.Objects;

/**
 * @author Peter Muys
 * @since 19/07/2016
 */
public class DbTypeRegistry extends BaseValueClass{

	private final PMap<String, DbType> reg;

	private DbTypeRegistry(PMap<String,DbType> reg){
		this.reg = Objects.requireNonNull(reg);
	}

	public static final DbTypeRegistry defaultInst = new DbTypeRegistry(PMap.empty())
		.add(DbDerby.inst)
		.add(DbH2.inst)
		.add(DbMySql.inst)
		.add(DbPostgres.inst);


	public DbTypeRegistry add(DbType type){
		return new DbTypeRegistry(reg.put(type.getDatabaseName(),type));
	}

	public Result<DbType> getDbType(DbConnector connector){
		return Result.function(connector).code(l-> connector.create()
			.flatMap(c -> {
				try{
					return getDbType(c);
				}finally {
					try{
						c.close();
					}catch(Exception e){
						throw new RuntimeException(e);
					}
				}
			}));
	}

	public Result<DbType> getDbType(String dbNameOrDbTypeClassName){
		return Result.function(dbNameOrDbTypeClassName).code((FunctionLogging l) ->
		   	reg.getResult(dbNameOrDbTypeClassName)
			   .flatMapEmpty(empty ->
				   UReflect.getClass(dbNameOrDbTypeClassName)
						 .flatMap(cls -> {
							 try {
								 return Result.success((DbType)cls.newInstance());
							 } catch(InstantiationException | IllegalAccessException | ClassCastException  e) {
								 return Result.<DbType>failure(e);
							 }
						 })
			   )
		);
	}

	public Result<DbType> getDbType(Connection c) {
		return Result.function(c).code(l ->  getDbType(c.getMetaData()));
	}

	public Result<DbType> getDbType(DatabaseMetaData md) {
		return Result.function(md).code(l -> {
			DbType res = reg.get(md.getDatabaseProductName());
			if(res == null) {
				Result.failure("Can't find Db Type for database with name '" + md.getDatabaseProductName() + "'");
			}
			return Result.success(res);
		});
	}
}
