package com.persistentbit.glasgolia.jaql.codegen;

import com.persistentbit.glasgolia.db.dbdef.DbMetaColumn;
import com.persistentbit.glasgolia.db.dbdef.DbMetaSchema;
import com.persistentbit.glasgolia.db.dbdef.DbMetaTable;

/**
 * TODOC
 *
 * @author petermuys
 * @since 22/06/17
 */
public interface DbJavaGenOptions{
	String	getPackage(DbMetaSchema schema, DbMetaTable table);
	String	javaName(DbMetaTable table);
	String  javaName(DbMetaTable table, DbMetaColumn column);
	String	javaType(DbMetaTable table, DbMetaColumn column);
}
