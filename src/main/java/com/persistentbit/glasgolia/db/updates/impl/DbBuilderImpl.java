package com.persistentbit.glasgolia.db.updates.impl;

import com.persistentbit.core.Lazy;
import com.persistentbit.core.OK;
import com.persistentbit.core.collections.PList;
import com.persistentbit.core.collections.PMap;
import com.persistentbit.core.result.Result;
import com.persistentbit.glasgolia.db.updates.DbBuilder;
import com.persistentbit.glasgolia.db.updates.SchemaUpdateHistory;
import com.persistentbit.glasgolia.db.work.DbWork;
import com.persistentbit.glasgolia.sql.scripts.SqlLoader;
import com.persistentbit.sql.PersistSqlException;

import java.lang.reflect.Method;
import java.sql.Statement;
import java.util.Optional;

/**
 * A {@link DbBuilder} implementation that uses resource files to create/update/drop a database schema.<br>
 * If a snippet exists with the name DropAll, then that snippet will be used in the {@link #dropAll()} method.<br>
 * if a snippet exists with the name OnceBefore, then that snippet will be run once before
 * every call to {@link #buildOrUpdate()} and {@link #dropAll()}.<br>
 *
 * @author Peter Muys
 * @see SchemaUpdateHistory
 * @since 18/06/16
 */
public class DbBuilderImpl implements DbBuilder{

	public static final String onceBeforeSnippetName = "OnceBefore";
	public static final String dropAllSnippetName    = "DropAll";

	protected final String              packageName;
	protected final SqlLoader           sqlLoader;
	protected final SchemaUpdateHistory updateHistory;


	/**
	 * Create a new {@link DbBuilder} with the given parameters and a default
	 * {@link SchemaUpdateHistoryImpl} to keep track of the updates already done.
	 *
	 * @param packageName     The packageName, used to keep track of the updates already done.
	 * @param sqlResourceName The name of the java resource file containing the sql statements
	 */
	public DbBuilderImpl(String packageName, String sqlResourceName) {
		this(packageName, sqlResourceName, new SchemaUpdateHistoryImpl());
	}

	/**
	 * @param packageName     The packageName, used to keep track of the updates already done.
	 * @param sqlResourceName The name of the java resource file containing the sql statements
	 * @param updateHistory   The {@link SchemaUpdateHistory} to use
	 */
	public DbBuilderImpl(String packageName, String sqlResourceName,
						 SchemaUpdateHistory updateHistory
	) {
		this.packageName = packageName;
		this.sqlLoader = new SqlLoader(sqlResourceName);
		this.updateHistory = updateHistory;
	}

	@Override
	public DbWork<OK> buildOrUpdate() {
		return DbWork.function().code(log -> ctx ->
			executeSnipIfExists(onceBeforeSnippetName)
				.flatMap(ok -> {
					PList<String> names = sqlLoader.getAllSnippetNames()
						.filter(name -> name
							.equalsIgnoreCase(dropAllSnippetName) == false && name
							.equalsIgnoreCase(onceBeforeSnippetName) == false);
					log.info("Found " + names.size() + " snippets");
					for(String name : names) {
						log.info("Executing snippet " + name);
						Result<OK> snipOk =
							executeSnip(name).execute(ctx);
						if(snipOk.isPresent() == false) {
							return snipOk;
						}
					}
					return OK.result;
				})
				.execute(ctx)
		);
	}

	private final Lazy<PMap<String, Method>> declaredMethods = new Lazy<>(() -> {
		PMap<String, Method> declaredMethods = PMap.empty();

		for(Method m : this.getClass().getDeclaredMethods()) {
			declaredMethods = declaredMethods.put(m.getName().toLowerCase(), m);
		}
		return declaredMethods;
	});

	private DbWork<OK> executeSnipIfExists(String name) {
		return DbWork.function(name).code(mainLog -> ctx -> Result.function().code(l -> {
			if(sqlLoader.hasSnippet(name)) {
				return executeSnip(name).execute(ctx);
			}
			return OK.result;
		}));
	}

	private DbWork<OK> executeSnip(String name) {
		return DbWork.function(name).code(mainLog -> ctx -> Result.function().code(l -> {
			//Is Snippet already executed ?
			if(updateHistory.isDone(packageName, name).execute(ctx).orElseThrow()) {
				return OK.result;
			}
			l.info("DBUpdate for  " + getFullName(name));
			//If a method with this name exists -> execute it
			Optional<Method> optMethod = declaredMethods.get().getOpt(name);
			if(optMethod.isPresent()) {
				return ctx.get().flatMapExc(con -> {
					optMethod.get().invoke(this, con);
					return OK.result;
				});

			}
			for(DbWork<OK> work : sqlLoader.getAll(name).map(sql -> executeSql(name, sql))) {
				Result<OK> ok = work.execute(ctx);
				if(ok.isPresent() == false) {
					return ok;
				}
			}
			if(name.equalsIgnoreCase(dropAllSnippetName) || name.equalsIgnoreCase(onceBeforeSnippetName)) {
				return OK.result;
			}
			return updateHistory.setDone(packageName, name).execute(ctx);
		}));
	}

	private String getFullName(String updateName) {
		return packageName + "." + updateName;
	}

	/**
	 * Execute an sql statement.
	 *
	 * @param name The name of the snippet for error reporting
	 * @param sql  The sql statement
	 */
	private DbWork<OK> executeSql(String name, String sql) {
		return ctx -> Result.function(name, sql).code(l -> ctx.get().flatMapExc(con -> {
			try(Statement stat = con.createStatement()) {
				stat.execute(sql);
				return OK.result;
			}
		}));

	}


	/**
	 * Executes the snippet 'DropAll' and removes
	 * the update history for this package.<br>
	 *
	 * @return true if dropAll executed without errors
	 */
	@Override
	public DbWork<OK> dropAll() {
		return ctx -> Result.function().code(l -> {
			if(sqlLoader.hasSnippet(dropAllSnippetName) == false) {
				return Result.failure(new PersistSqlException("Can't find SQL code 'DropAll' in " + sqlLoader));
			}
			return executeSnipIfExists(onceBeforeSnippetName)
				.andThen(ok -> executeSnip(dropAllSnippetName))
				.andThen(ok -> updateHistory.removeUpdateHistory(packageName))
				.execute(ctx);
		});

	}

	@Override
	public DbWork<Boolean> hasUpdatesThatAreDone() {
		return ctx -> Result.function().code(l ->
			updateHistory.getUpdatesDone(packageName)
			   .map(p -> p.isEmpty() == false)
			   .execute(ctx)
		);
	}
}
