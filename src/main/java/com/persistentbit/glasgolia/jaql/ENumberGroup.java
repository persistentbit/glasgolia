package com.persistentbit.glasgolia.jaql;

import com.persistentbit.glasgolia.jaql.expr.EGroup;
import com.persistentbit.glasgolia.jaql.expr.ETypeNumber;
import com.persistentbit.glasgolia.jaql.expr.Expr;

/**
 * Created by petermuys on 5/10/16.
 */
public class ENumberGroup<T extends Number> extends EGroup<T> implements ETypeNumber<T>{

	public ENumberGroup(Expr<T> value) {
		super(value);
	}
}
