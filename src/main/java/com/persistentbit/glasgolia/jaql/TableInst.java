package com.persistentbit.glasgolia.jaql;

import com.persistentbit.core.utils.BaseValueClass;
import com.persistentbit.glasgolia.jaql.expr.ETypeObject;

/**
 * Created by petermuys on 2/10/16.
 */
public class TableInst extends BaseValueClass{

	private String      name;
	private ETypeObject table;

	public TableInst(String name, ETypeObject table) {
		this.name = name;
		this.table = table;
	}

	@Override
	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public ETypeObject getTable() {
		return table;
	}
}
