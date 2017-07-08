package com.persistentbit.glasgolia.db.work;

import com.persistentbit.core.Nullable;
import com.persistentbit.core.OK;
import com.persistentbit.core.logging.Log;
import com.persistentbit.core.result.Result;
import com.persistentbit.glasgolia.db.connections.DbConnector;
import com.persistentbit.glasgolia.db.types.DbType;

import java.sql.SQLException;
import java.util.Objects;

/**
 * TODOC
 *
 * @author petermuys
 * @since 3/06/17
 */
public class DbRun{


	private final DbConnector  connector;
	@Nullable
	private       DbType dbType;
	@Nullable
	private final String         schema;

	public DbRun(DbConnector connector, @Nullable DbType dbType, @Nullable String schema) {
		this.connector = Objects.requireNonNull(connector);
		this.dbType = dbType;
		this.schema = schema;
	}

	public DbType getDbType() {
		return dbType;
	}

	public static DbRun create(DbConnector connector, DbType type) {
		return new DbRun(connector, type, null);
	}

	public static DbRun create(DbConnector connector) {
		return create(connector, null);
	}
	public DbRun withSchema(String schema) {
		return new DbRun(connector, dbType, schema);
	}

	public <R> Result<R> run(DbWork<R> work) {
		DbWorkContext ctx = DbWorkContext.create(connector,dbType).withSchema(schema);
		Result<R> result;
		try {
			result = work.execute(ctx);
		} catch(Exception e) {
			result = Result.failure(e);
		}
		Result<R> finalResult = result;
		return Result.function()
			.code(l -> finalResult
				.ifFailure(failure -> {
					l.warning("Rolling back database transaction.");
					ctx.get().ifPresent(succ -> {
						try {
							succ.getValue().rollback();
						} catch(SQLException se) {
							l.exception(se);
						}
					});

					close(ctx);
				})

				.ifPresent(success -> {
					l.warning("Commit database transaction on success");
					ctx.get().ifPresent(succ -> {
						try {
							succ.getValue().commit();
						} catch(SQLException se) {
							l.exception(se);
						}
					});
					close(ctx);
				})
				.ifEmpty(empty -> {
					l.warning("Commit database transaction on empty result");
					ctx.get().ifPresent(succ -> {
						try {
							succ.getValue().commit();
						} catch(SQLException se) {
							l.exception(se);
						}
					});

					close(ctx);
				})
			);

	}

	private void close(DbWorkContext ctx) {
		Log.function().code(l -> {
			ctx.get().ifPresent(succ -> {
				try {
					succ.getValue().close();
				} catch(Exception e) {
					l.exception(e);
				}
			});
			return OK.inst;
		});
	}
}
