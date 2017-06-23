package com.persistentbit.glasgolia;

import com.persistentbit.core.ModuleCore;
import com.persistentbit.core.OK;
import com.persistentbit.core.logging.printing.LogPrint;
import com.persistentbit.core.result.Result;
import com.persistentbit.glasgolia.db.connections.DbConnector;
import com.persistentbit.glasgolia.db.connections.DbPoolConnector;
import com.persistentbit.glasgolia.db.connections.DbSimpleConnector;
import com.persistentbit.glasgolia.db.types.DbPostgres;
import com.persistentbit.glasgolia.db.updates.DbBuilder;
import com.persistentbit.glasgolia.db.updates.impl.DbBuilderImpl;
import com.persistentbit.glasgolia.db.work.DbRun;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/06/17
 */
public abstract class AbstractDbTest{
	public static final LogPrint lp = ModuleCore.consoleLogPrint;



	public static final DbConnector	postgresConnector = DbSimpleConnector.create(
		DbPostgres.getDriverClassName(),DbPostgres.connectionUrl("localhost","glasgolia","glasschema"),
		"glasgolia_usr","glasgolia_pwd")
		.map(simpleConnector -> new DbPoolConnector(simpleConnector,10))
		.orElseThrow();
	protected static final DbRun postgresRun = DbRun.create(postgresConnector);
	protected static final DbBuilder	postgresBuilder = new DbBuilderImpl("com.persistentbit.glasgolia.postgres","/db/postgres_testdb.sql");

	protected static Result<OK> resetPostgres() {
		return postgresRun.run(postgresBuilder.resetDb());
	}
}
