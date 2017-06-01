package com.persistentbit.glasgolia.jaql.expr.mixins;

import com.persistentbit.glasgolia.jaql.expr.ETypeBoolean;
import com.persistentbit.glasgolia.jaql.expr.Expr;
import com.persistentbit.glasgolia.jaql.expr.ExprCompare;
import com.persistentbit.glasgolia.jaql.expr.ExprIsNull;

/**
 * @author Peter Muys
 * @since 28/09/2016
 */
public interface MixinEq<T extends Expr>{

	default ETypeBoolean eq(T right) {
		return new ExprCompare((T) this, right, ExprCompare.CompType.eq);
	}

	default ETypeBoolean notEq(T right) {
		return new ExprCompare((T) this, right, ExprCompare.CompType.neq);
	}

	default ETypeBoolean isNull() {
		return new ExprIsNull((T) this, false);
	}

	default ETypeBoolean isNotNull() {
		return new ExprIsNull((T) this, true);
	}
}
