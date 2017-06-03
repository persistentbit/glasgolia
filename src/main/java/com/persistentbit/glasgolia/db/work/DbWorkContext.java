package com.persistentbit.glasgolia.db.work;

import com.persistentbit.core.Nullable;
import com.persistentbit.core.result.Result;
import com.persistentbit.core.utils.BaseValueClass;
import com.persistentbit.glasgolia.db.connections.DbConnector;
import com.persistentbit.glasgolia.db.transactions.DbTransaction;
import com.persistentbit.glasgolia.db.types.DbType;
import com.persistentbit.glasgolia.db.types.DbTypeRegistry;

import java.sql.Connection;
import java.util.Objects;

/**
 * TODOC
 *
 * @author petermuys
 * @since 2/06/17
 */
public class DbWorkContext extends BaseValueClass{

	private final DbTransaction  transaction;
	private       Result<DbType> dbType;
	@Nullable
	private final String         schema;

	DbWorkContext(DbTransaction transaction, Result<DbType> dbType, @Nullable String schema) {
		this.transaction = Objects.requireNonNull(transaction);
		this.dbType = Objects.requireNonNull(dbType);
		this.schema = schema;
	}



	public static DbWorkContext create(DbConnector connector, DbType type) {
		return new DbWorkContext(DbTransaction.create(connector), Result.result(type), null);
	}

	public static DbWorkContext create(DbConnector connector) {
		return create(connector, null);
	}

	public DbWorkContext withSchema(String schema) {
		return new DbWorkContext(transaction, dbType, schema);
	}

	public DbWorkContext newTransaction() {
		return new DbWorkContext(transaction.createNew(), dbType, schema);
	}

	public Result<Connection> get() {
		return transaction.get();
	}

	public Result<DbType> getDbType() {
		if(dbType.isEmpty()) {
			dbType = get().flatMap(DbTypeRegistry.defaultInst::getDbType);
		}
		return dbType;
	}

	public Result<String> getSchema() {
		return Result.result(schema);
	}
	public String getFullTableName(String tableName) {
		return schema == null
			? tableName
			: schema + "." + tableName;
	}
}
