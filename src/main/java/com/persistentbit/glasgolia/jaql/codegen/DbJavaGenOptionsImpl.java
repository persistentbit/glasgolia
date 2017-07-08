package com.persistentbit.glasgolia.jaql.codegen;

import com.persistentbit.glasgolia.db.dbdef.DbMetaSchema;
import com.persistentbit.glasgolia.db.dbdef.DbMetaTable;
import com.persistentbit.glasgolia.db.types.DbType;

/**
 * TODOC
 *
 * @author petermuys
 * @since 8/07/17
 */
public class DbJavaGenOptionsImpl implements DbJavaGenOptions{
	private final DbType	dbType;
	private final String	defaultPackage;

	public DbJavaGenOptionsImpl(DbType dbType, String defaultPackage ) {
		this.dbType = dbType;
		this.defaultPackage = defaultPackage;
	}

	@Override
	public String getPackage(DbMetaSchema schema, DbMetaTable table
	) {
		return defaultPackage + "." + javaName(schema).map(schemaName -> schemaName + "." + javaName(table)).orElse(javaName(table));
	}
}
