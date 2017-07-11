package com.persistentbit.glasgolia.jaql.codegen.posgresql;

import com.persistentbit.glasgolia.db.work.DbRun;

/**
 * TODOC
 *
 * @author petermuys
 * @since 11/07/17
 */
public class PostgresJavaGen{
	private final JavaGenTableSelection	selection;
	private final DbRun run;

	public PostgresJavaGen(JavaGenTableSelection selection) {
		this.selection = selection;
		this.run = selection.getRun();
	}

	public JavaGenTableSelection	getSelection() {
		return selection;
	}
}
