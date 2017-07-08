package com.persistentbit.glasgolia.jaql.codegen;

import com.persistentbit.core.collections.PList;

/**
 * TODOC
 *
 * @author petermuys
 * @since 8/07/17
 */
public class DbColumnJavaGen{
	private final String fieldDef;
	private final PList<String> imports;

	public DbColumnJavaGen(String fieldDef, PList<String> imports) {
		this.fieldDef = fieldDef;
		this.imports = imports;
	}
	public static DbColumnJavaGen create(String fieldDef, String...imports){
		return new DbColumnJavaGen(fieldDef,PList.val(imports));
	}

	public static DbColumnJavaGen create(Class cls){
		return create(cls.getSimpleName(), cls.getName());
	}

	public String getFieldDef() {
		return fieldDef;
	}

	public PList<String> getImports() {
		return imports;
	}
}
