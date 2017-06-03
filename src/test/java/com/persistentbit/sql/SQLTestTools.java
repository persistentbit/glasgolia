package com.persistentbit.sql;

import com.persistentbit.core.logging.printing.LogFormatter;
import com.persistentbit.core.logging.printing.LogPrint;
import com.persistentbit.core.logging.printing.LogPrintStream;
import com.persistentbit.glasgolia.db.connections.DbConnector;
import com.persistentbit.glasgolia.db.connections.DbPoolConnector;
import com.persistentbit.glasgolia.db.connections.DbSimpleConnector;
import com.persistentbit.glasgolia.db.types.DbDerby;
import com.persistentbit.glasgolia.db.types.DbType;
import com.persistentbit.glasgolia.db.updates.DbBuilder;
import com.persistentbit.glasgolia.db.updates.impl.DbBuilderImpl;
import com.persistentbit.glasgolia.db.work.DbRun;

/**
 * TODOC
 *
 * @author petermuys
 * @since 14/01/17
 */
public class SQLTestTools{

	static final LogFormatter logFormatter    = ModuleSql.createLogFormatter(true);
	static final LogPrint     logPrint        = LogPrintStream.sysOut(logFormatter);
	static final DbType       testDbType      = DbDerby.inst;
	static final String       testSchema      = null;
	static final DbConnector  testDbConnector = DbSimpleConnector.registerDriver(DbDerby.getDriverClassName())
													.map(ok -> new DbSimpleConnector(DbDerby.urlInMemory(testSchema),null,null))
													.<DbConnector>map( scon -> new DbPoolConnector(scon,2))
													.orElseThrow();

	static final DbRun dbRun = DbRun.create(testDbConnector, testDbType).withSchema(testSchema);

	static final DbBuilder builder = new DbBuilderImpl("sqlTests", "/db/db_update.sql");


}


