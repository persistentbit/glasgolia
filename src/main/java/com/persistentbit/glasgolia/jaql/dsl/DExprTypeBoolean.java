package com.persistentbit.glasgolia.jaql.dsl;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/06/17
 */
public interface DExprTypeBoolean extends DExpr<Boolean>{
	default DExprTypeBoolean	not(){
		return new DExprNot(this);
	}
}
