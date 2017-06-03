package com.persistentbit.glasgolia.jaql.expr;

import com.persistentbit.core.collections.PList;
import com.persistentbit.core.collections.PMap;
import com.persistentbit.core.tuples.Tuple2;
import com.persistentbit.glasgolia.db.types.DbType;
import com.persistentbit.glasgolia.db.types.DbUnknownType;
import com.persistentbit.glasgolia.db.work.DbWorkContext;

import java.sql.PreparedStatement;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Context used by {@link Expr} instances to generate Sql code
 *
 * @author Peter Muys
 * @since 14/10/16
 */
public class ExprToSqlContext{

	private final DbWorkContext dbContext;
	private       boolean       useSqlParams;
	private int                                                 nextUniqueId       = 1;
	private PMap<Expr, String>                                  instanceNameLookup = PMap.empty();
	private PList<Consumer<Tuple2<PreparedStatement, Integer>>> paramSetters       = PList.empty();

	public ExprToSqlContext(DbWorkContext dbContext, boolean useSqlParams) {
		this.dbContext = dbContext;
		this.useSqlParams = useSqlParams;
	}

	public DbWorkContext getDbContext() {
		return dbContext;
	}

	public String uniqueInstanceName(Expr expr, String defaultName) {
		String res = instanceNameLookup.getOrDefault(expr, null);
		if(res == null) {
			res = defaultName + "_" + nextUniqueId;
			nextUniqueId++;
			instanceNameLookup = instanceNameLookup.put(expr, res);
		}
		return res;
	}

	public Optional<String> getSchema() {
		return dbContext.getSchema().getOpt();
	}

	public DbType getDbType() {
		return dbContext.getDbType().orElse(DbUnknownType.inst);
	}

	public void setParam(Consumer<Tuple2<PreparedStatement, Integer>> re) {
		if(useSqlParams) {
			paramSetters = paramSetters.plus(re);
		}
	}

	public boolean isUsingSqlParameters() {
		return useSqlParams;
	}

	public void setUseSqlParams(boolean useSqlParams) {
		this.useSqlParams = useSqlParams;
	}

	public PList<Consumer<Tuple2<PreparedStatement, Integer>>> getParamSetters() {
		return paramSetters;
	}
}
