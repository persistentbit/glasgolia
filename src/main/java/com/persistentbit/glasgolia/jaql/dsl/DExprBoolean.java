package com.persistentbit.glasgolia.jaql.dsl;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/06/17
 */
public class DExprBoolean	implements DExpr<Boolean>, DExprTypeBoolean{

	private final Boolean value;

	public DExprBoolean(Boolean value) {
		this.value = value;
	}
}
