package com.persistentbit.glasgolia.db.transactions;

import com.persistentbit.core.result.Result;
import com.persistentbit.glasgolia.db.connections.DbConnector;

import java.sql.Connection;

/**
 * TODOC
 *
 * @author petermuys
 * @since 2/06/17
 */
class DbTransactionImpl implements DbTransaction{

	private final DbConnector        connectionSupplier;
	private       Result<Connection> connection;

	public DbTransactionImpl(DbConnector connectionSupplier) {
		this.connectionSupplier = connectionSupplier;
		this.connection = connectionSupplier.create();
	}

	@Override
	public String toString() {
		return "DbTransactionImpl[]";
	}

	@Override
	public DbTransaction createNew() {
		return new DbTransactionImpl(connectionSupplier);
	}

	@Override
	public Result<Connection> get() {
		return connection;
	}

	/*public <R> Result<R> run(SqlWork<R> work) {
		Result<R> result;
		try {
			result = work.execute(this);
		} catch(Exception e) {
			result = Result.failure(e);
		}
		Result<R> finalResult = result;
		return Result.function().code(l ->
										  finalResult
											  .ifFailure(failure -> {
												  l.warning("Rolling back database transaction.");
												  try {
													  get().rollback();
												  } catch(SQLException se) {
													  l.exception(se);
												  }
												  close();
											  })

											  .ifPresent(success -> {
												  l.warning("Commit database transaction on success");
												  try {
													  get().commit();
												  } catch(SQLException se) {
													  l.exception(se);
												  }
												  close();
											  })
											  .ifEmpty(empty -> {
														   l.warning("Commit database transaction on empty result");
														   try {
															   get().commit();
														   } catch(SQLException se) {
															   l.exception(se);
														   }
														   close();
													   }
											  )
		);
	}

	private void close() {
		Log.function().code(l -> {
			get().close();
			return OK.inst;
		});
	}*/
}
