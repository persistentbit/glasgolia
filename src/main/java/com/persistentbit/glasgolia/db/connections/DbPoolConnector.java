package com.persistentbit.glasgolia.db.connections;

import com.persistentbit.core.OK;
import com.persistentbit.core.doc.annotations.DUsedByClass;
import com.persistentbit.core.result.Result;
import com.persistentbit.sql.PersistSqlException;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * A {@link Connection} supplier that uses a connection pool to return new connections.<br>
 *
 * @author Peter Muys
 * @since 13/07/2016
 */

public class DbPoolConnector implements DbConnector{


	private final DbConnector               supplier;
	private final Consumer<Connection>      resetter;
	private final int                       poolSize;
	private final BlockingQueue<Connection> freeConnections;
	private       int                       activeConnections;


	public DbPoolConnector(DbConnector supplier, int poolSize) {
		this(supplier, poolSize, (c) -> {
			try {
				c.setAutoCommit(false);
			} catch(SQLException e) {
				throw new PersistSqlException(e);
			}
		});
	}

	public DbPoolConnector(DbConnector supplier, int poolSize,
						   Consumer<Connection> connectionResetter
	) {
		this.supplier = supplier;
		this.poolSize = poolSize;
		this.resetter = connectionResetter;
		this.freeConnections = new LinkedBlockingQueue(poolSize);
	}

	@Override
	public Result<Connection> create() {
		return Result.function().code(log -> {
			if(freeConnections.isEmpty()) {
				if(activeConnections < poolSize) {
					//Nog geen pool opgebouwd...
					return supplier.create()
						.<Connection>map(this::newConnection)
						.ifPresent(success -> activeConnections++);
				}
			}
			Connection con = null;
			do {
				try {
					con = freeConnections.poll(1000, TimeUnit.MILLISECONDS);
				} catch(InterruptedException e) {
					log.warning("Waiting for a free connection...");
				}
			} while(con == null);
			if(con.isValid(0) == false) {
				return supplier.create().map(this::newConnection);
			}
			return Result.success(newConnection(con));
		});
	}



	private ConnectionWrapper newConnection(Connection realConnection) {
		resetter.accept(realConnection);
		ConnectionWrapper con = new ConnectionWrapper(realConnection, new ConnectionWrapper.ConnectionHandler(){
			private boolean isCommit = false;

			@Override
			public void onClose(Connection connection) throws SQLException {
				if(isCommit == false || connection.getAutoCommit()) {
					connection.rollback();
				}
				freeConnections.add(connection);

			}

			@Override
			public void onCommit(Connection connection) throws SQLException {
				isCommit = true;
				connection.commit();
			}

			@Override
			public void onRollback(Connection connection) throws SQLException {
				isCommit = true;
				connection.rollback();
			}

			@Override
			public void onAbort(Connection connection, Executor executor) throws SQLException {
				activeConnections--;
				connection.abort(executor);
			}
		});
		return con;
	}

	public synchronized Result<OK> close() {
		return Result.function().code(log -> {
			int inuse = activeConnections - freeConnections.size();
			if(inuse > 0) {
				log.warning("Closing the connection pool with " + inuse + " connections still in use");
			}
			else {
				log.info("Closing the connection pool with " + activeConnections + " open connections");
			}
			while(activeConnections > 0) {
				Connection con = null;
				try {
					for(int t = 0; t < 20; t++) {
						con = freeConnections.poll(1000, TimeUnit.MILLISECONDS);
						if(con != null) {
							break;
						}
						log.warning("Waiting for the release of a connection");
					}
					try {
						con.close();
					} catch(SQLException e) {
						log.exception(e);
					}
					activeConnections--;
				} catch(InterruptedException e) {
					log.exception(e);
				}
			}
			return OK.result;
		});

	}
	@DUsedByClass(DbPoolConnector.class)
	private static class ConnectionWrapper implements Connection{

		private final Connection                                                                          master;
		private final ConnectionHandler handler;

		public ConnectionWrapper(Connection master, ConnectionHandler handler) {
			this.master = master;
			this.handler = handler;
		}

		@Override
		public boolean isWrapperFor(Class<?> iface) throws SQLException {
			return master.isWrapperFor(iface);
		}

		@Override
		public Statement createStatement() throws SQLException {
			return master.createStatement();
		}

		@Override
		public PreparedStatement prepareStatement(String sql) throws SQLException {
			return master.prepareStatement(sql);
		}

		@Override
		public CallableStatement prepareCall(String sql) throws SQLException {
			return master.prepareCall(sql);
		}

		@Override
		public String nativeSQL(String sql) throws SQLException {
			return master.nativeSQL(sql);
		}

		@Override
		public boolean getAutoCommit() throws SQLException {
			return master.getAutoCommit();
		}

		@Override
		public void setAutoCommit(boolean autoCommit) throws SQLException {
			master.setAutoCommit(autoCommit);
		}

		@Override
		public void commit() throws SQLException {
			handler.onCommit(master);
		}

		@Override
		public void rollback() throws SQLException {
			handler.onRollback(master);
		}

		@Override
		public void close() throws SQLException {
			handler.onClose(master);
		}

		@Override
		public boolean isClosed() throws SQLException {
			return master.isClosed();
		}

		@Override
		public DatabaseMetaData getMetaData() throws SQLException {
			return master.getMetaData();
		}

		@Override
		public boolean isReadOnly() throws SQLException {
			return master.isReadOnly();
		}

		@Override
		public void setReadOnly(boolean readOnly) throws SQLException {
			master.setReadOnly(readOnly);
		}

		@Override
		public String getCatalog() throws SQLException {
			return master.getCatalog();
		}

		@Override
		public void setCatalog(String catalog) throws SQLException {
			master.setCatalog(catalog);
		}

		@Override
		public int getTransactionIsolation() throws SQLException {
			return master.getTransactionIsolation();
		}

		@Override
		public void setTransactionIsolation(int level) throws SQLException {
			master.setTransactionIsolation(level);
		}

		@Override
		public SQLWarning getWarnings() throws SQLException {
			return master.getWarnings();
		}

		@Override
		public void clearWarnings() throws SQLException {
			master.clearWarnings();
		}

		@Override
		public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
			return master.createStatement(resultSetType, resultSetConcurrency);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency
		) throws SQLException {
			return master.prepareStatement(sql, resultSetType, resultSetConcurrency);
		}

		@Override
		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
			return master.prepareCall(sql, resultSetType, resultSetConcurrency);
		}

		@Override
		public Map<String, Class<?>> getTypeMap() throws SQLException {
			return master.getTypeMap();
		}

		@Override
		public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
			master.setTypeMap(map);
		}

		@Override
		public int getHoldability() throws SQLException {
			return master.getHoldability();
		}

		@Override
		public void setHoldability(int holdability) throws SQLException {
			master.setHoldability(holdability);
		}

		@Override
		public Savepoint setSavepoint() throws SQLException {
			return master.setSavepoint();
		}

		@Override
		public Savepoint setSavepoint(String name) throws SQLException {
			return master.setSavepoint(name);
		}

		@Override
		public void rollback(Savepoint savepoint) throws SQLException {
			master.rollback(savepoint);
		}

		@Override
		public void releaseSavepoint(Savepoint savepoint) throws SQLException {
			master.releaseSavepoint(savepoint);
		}

		@Override
		public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability
		) throws SQLException {
			return master.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
												  int resultSetHoldability
		) throws SQLException {
			return master.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		@Override
		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
											 int resultSetHoldability
		) throws SQLException {
			return master.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
			return master.prepareStatement(sql, autoGeneratedKeys);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
			return master.prepareStatement(sql, columnIndexes);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
			return master.prepareStatement(sql, columnNames);
		}

		@Override
		public Clob createClob() throws SQLException {
			return master.createClob();
		}

		@Override
		public Blob createBlob() throws SQLException {
			return master.createBlob();
		}

		@Override
		public NClob createNClob() throws SQLException {
			return master.createNClob();
		}

		@Override
		public SQLXML createSQLXML() throws SQLException {
			return master.createSQLXML();
		}

		@Override
		public boolean isValid(int timeout) throws SQLException {
			return master.isValid(timeout);
		}

		@Override
		public void setClientInfo(String name, String value) throws SQLClientInfoException {
			master.setClientInfo(name, value);
		}

		@Override
		public String getClientInfo(String name) throws SQLException {
			return master.getClientInfo(name);
		}

		@Override
		public Properties getClientInfo() throws SQLException {
			return master.getClientInfo();
		}

		@Override
		public void setClientInfo(Properties properties) throws SQLClientInfoException {
			master.setClientInfo(properties);
		}

		@Override
		public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
			return master.createArrayOf(typeName, elements);
		}

		@Override
		public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
			return master.createStruct(typeName, attributes);
		}

		@Override
		public String getSchema() throws SQLException {
			return master.getSchema();
		}

		@Override
		public void setSchema(String schema) throws SQLException {
			master.setSchema(schema);
		}

		@Override
		public void abort(Executor executor) throws SQLException {
			master.abort(executor);
		}

		@Override
		public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
			master.setNetworkTimeout(executor, milliseconds);
		}

		@Override
		public int getNetworkTimeout() throws SQLException {
			return master.getNetworkTimeout();
		}

		@Override
		public <T> T unwrap(Class<T> iface) throws SQLException {
			return master.unwrap(iface);
		}

		public interface ConnectionHandler{

			default void onClose(Connection connection) throws SQLException {
				connection.close();
			}

			default void onCommit(Connection connection) throws SQLException {
				connection.commit();
			}

			default void onRollback(Connection connection) throws SQLException {
				connection.rollback();
			}

			default void onAbort(Connection connection, Executor executor) throws SQLException {
				connection.abort(executor);
			}
		}

	}
}
