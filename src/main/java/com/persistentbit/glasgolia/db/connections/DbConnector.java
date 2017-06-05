package com.persistentbit.glasgolia.db.connections;

import com.persistentbit.core.result.Result;

import java.sql.Connection;

/**
 * A Supplier of a database {@link Connection}
 *
 * @author petermuys
 * @since 2/06/17
 * @see DbDatasourceConnector
 * @see DbPoolConnector
 * @see DbSimpleConnector
 */
@FunctionalInterface
public interface DbConnector{

	/**
	 * Create a new Database connection.<br>
	 * AutoCommit on the connection should be disabled.<br>
	 *
	 * @return The Result with the new Connection.
	 */
	Result<Connection>	create();
}
