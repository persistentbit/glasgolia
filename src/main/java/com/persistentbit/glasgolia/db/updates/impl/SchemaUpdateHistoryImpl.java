package com.persistentbit.glasgolia.db.updates.impl;

import com.persistentbit.core.OK;
import com.persistentbit.core.collections.PList;
import com.persistentbit.core.logging.Log;
import com.persistentbit.core.result.Result;
import com.persistentbit.glasgolia.db.updates.SchemaUpdateHistory;
import com.persistentbit.glasgolia.db.work.DbWork;
import com.persistentbit.glasgolia.db.work.DbWorkContext;
import com.persistentbit.sql.PersistSqlException;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Implements A {@link SchemaUpdateHistory} interface by using a db table<br>
 */
public class SchemaUpdateHistoryImpl implements SchemaUpdateHistory{

	private final String tableName;


	/**
	 * Creates an instance with 'schema_history' as table name and no schema name
	 */
	public SchemaUpdateHistoryImpl() {
		this("schema_history");
	}


	/**
	 * @param tableName The table name for the schema history table.
	 */
	public SchemaUpdateHistoryImpl(String tableName) {
		this.tableName = tableName;
	}


	@Override
	public DbWork<Boolean> isDone(String packageName, String updateName) {
		return DbWork.function(packageName, updateName).code(log -> ctx ->
			createTableIfNotExist()
				.flatMap(ok -> {
					String sql = "select count(1) from " + ctx.getFullTableName(tableName) +
						" where package_name=?  and update_name=?";
					log.info("Executing " + sql);
					return ctx.get().flatMapExc(con -> {
						try(PreparedStatement stat = con.prepareStatement(sql)) {
							stat.setString(1, packageName);
							stat.setString(2, updateName);
							try(ResultSet rs = stat.executeQuery()) {
								rs.next();
								return Result.success(rs.getInt(1));
							}
						}
					});

				})
				.map(count -> count > 0)
				.execute(ctx)
		);
	}

	private DbWork<OK> createTableIfNotExist() {
		return DbWork.function().code(log -> ctx ->
			schemaHistoryTableExists()
				.flatMap(exists -> {
					if(exists == false) {
						return ctx.get().flatMapExc(con -> {
							try(Statement stat = con.createStatement()) {
								stat.execute("CREATE TABLE " + ctx.getFullTableName(tableName) + " (" +
												 "  createdDate TIMESTAMP          NOT NULL DEFAULT current_timestamp," +
												 "  package_name  VARCHAR(80)        NOT NULL," +
												 "  update_name  VARCHAR(80)        NOT NULL," +
												 "  CONSTRAINT " + tableName + "_uc UNIQUE (package_name,update_name)" +
												 ")");
							}
							return OK.result;
						});

					}
					return OK.result;
				})
				.execute(ctx)
		);
	}

	private DbWork<Boolean> schemaHistoryTableExists() {
		return DbWork.function().code(l -> ctx ->
			Result.success(
				tableExists(ctx, tableName) ||
					tableExists(ctx, tableName.toLowerCase()) ||
					tableExists(ctx, tableName.toUpperCase())
			)
		);
	}

	private boolean tableExists(DbWorkContext ctx, String tableName) {
		return Log.function(tableName).code(l -> {
			DatabaseMetaData dbm = ctx.get().orElseThrow().getMetaData();

			try(ResultSet rs = dbm.getTables(
				null,
				ctx.getSchema().orElse(null),
				tableName, null
			)) {
				while(rs.next()) {
					String tn = rs.getString("table_name");
					if(tableName.equalsIgnoreCase(tableName)) {
						return true;
					}
				}
				return false;
			}
		});
	}

	@Override
	public DbWork<PList<String>> getUpdatesDone(String packageName) {
		return ctx -> Result.function(packageName).code(l ->
			ctx.get().flatMapExc(con ->
				schemaHistoryTableExists()
					.flatMap(exists -> {
						PList<String> result = PList.empty();
						if(exists) {
							try(PreparedStatement stat = con.prepareStatement("select update_name from " +
									ctx.getFullTableName(tableName) +
													  " where package_name=?")) {
								stat.setString(1, packageName);

								try(ResultSet rs = stat
									.executeQuery()) {
									while(rs.next()) {
										result = result
											.plus(rs.getString(1));
									}

								}
							}
						}
						return Result.success(result);
					})
					.execute(ctx)
			)

		);

	}


	@Override
	public DbWork<OK> setDone(String packageName, String updateName) {
		DbWork<OK> insert = ctx -> {
			String sql = "insert into " + ctx.getFullTableName(tableName) +
				"(package_name,update_name) values(?,?)";
			return ctx.get().flatMapExc(con -> {
				try(PreparedStatement s = con.prepareStatement(sql)) {
					s.setString(1, packageName);
					s.setString(2, updateName);
					if(s.executeUpdate() != 1) {
						return Result
							.failure(new PersistSqlException("Expected 1 update for " + packageName + "." + updateName));
					}
				}
				return OK.result;
			});

		};
		return ctx -> Result.function(packageName, updateName).code(l ->
				createTableIfNotExist()
				  .flatMap(ok -> insert
					  .execute(ctx))
						  .execute(ctx)
		);
	}

	@Override
	public DbWork<OK> removeUpdateHistory(String packageName) {
		DbWork<OK> delete = ctx -> ctx.get().flatMapExc(con -> {
			String sql = "delete from " + ctx.getFullTableName(tableName) + " where package_name = ?";
			try(PreparedStatement s = con.prepareStatement(sql)) {
				s.setString(1, packageName);
				s.executeUpdate();
			}
			try(PreparedStatement s = con.prepareStatement("select count(1) from " + ctx.getFullTableName(tableName))) {
				int count = 0;
				try(ResultSet rs = s.executeQuery()) {
					rs.next();
					count = rs.getInt(1);
				}
				if(count == 0) {
					try(PreparedStatement ds = con.prepareStatement("drop table " + ctx.getFullTableName(tableName))) {
						ds.executeUpdate();
					}
				}

			}
			return OK.result;
		});

		return ctx -> Result.function(packageName).code(l ->
																  schemaHistoryTableExists()
																	  .flatMap(exists -> {
																		  if(exists) {
																			  return delete.execute(ctx);
																		  }
																		  return OK.result;
																	  })
																	  .execute(ctx)
		);


	}
}

