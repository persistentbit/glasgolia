package com.persistentbit.sql;

import com.persistentbit.core.logging.printing.LogFormatter;
import com.persistentbit.core.logging.printing.LogPrint;
import com.persistentbit.core.logging.printing.LogPrintStream;
import com.persistentbit.glasgolia.db.connections.pool.PooledConnectionSupplier;
import com.persistentbit.glasgolia.db.connections.SimpleConnectionSupplier;
import com.persistentbit.glasgolia.db.types.DbDerby;
import com.persistentbit.glasgolia.db.types.DbType;
import com.persistentbit.glasgolia.db.updates.DbBuilder;
import com.persistentbit.glasgolia.db.updates.impl.DbBuilderImpl;
import com.persistentbit.glasgolia.jaql.DbContext;
import com.persistentbit.glasgolia.jaql.DbWorkRunner;

import java.sql.Connection;
import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 14/01/17
 */
public class SQLTestTools{

	static final LogFormatter         logFormatter    = ModuleSql.createLogFormatter(true);
	static final LogPrint             logPrint        = LogPrintStream.sysOut(logFormatter);
	static final DbType               testDbType      = new DbDerby();
	static final String               testSchema      = null;
	static final Supplier<Connection> testDbConnector = new PooledConnectionSupplier(new SimpleConnectionSupplier(
		"org.apache.derby.jdbc.EmbeddedDriver",
		DbDerby.urlInMemory(testSchema)
	),2);

	static final DbContext    testDbContext = DbContext.of(testDbType, testSchema);
	static final DbWorkRunner dbRun         = DbWorkRunner.create(testDbConnector, testDbContext);

	static final DbBuilder builder = new DbBuilderImpl("sqlTests", "/db/db_update.sql");


}


