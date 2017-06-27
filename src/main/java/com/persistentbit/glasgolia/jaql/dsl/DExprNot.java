package com.persistentbit.glasgolia.jaql.dsl;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/06/17
 */
public class DExprNot implements DExpr<Boolean>, DExprTypeBoolean{
	private DExpr<Boolean> value;

	public DExprNot(DExpr<Boolean> value) {
		this.value = value;
	}
}
