package com.persistentbit.glasgolia.jaql.expr;

import com.persistentbit.glasgolia.jaql.ExprRowReaderCache;
import com.persistentbit.glasgolia.jaql.RowReader;
import com.persistentbit.glasgolia.jaql.expr.mixins.MixinEq;

import java.time.LocalDateTime;

/**
 * A {@link LocalDateTime} {@link Expr} type with default methods
 *
 * @author Peter Muys
 * @since 4/10/16
 */
public interface ETypeDateTime extends Expr<LocalDateTime>, MixinEq<ETypeDateTime>{

	default ETypeBoolean eq(LocalDateTime dateTime) {
		return eq(Sql.val(dateTime));
	}

	default ETypeBoolean notEq(LocalDateTime dateTime) {
		return notEq(Sql.val(dateTime));
	}

	@Override
	default LocalDateTime read(RowReader _rowReader, ExprRowReaderCache _cache) {
		return _rowReader.readNext(LocalDateTime.class);
	}

	default ETypeBoolean between(Expr<LocalDateTime> left, LocalDateTime right) {
		return between(left, Sql.val(right));
	}

	//***************************  BETWEEN
	default ETypeBoolean between(Expr<LocalDateTime> left, Expr<LocalDateTime> right) {
		return new ExprBetween<>(this, left, right);
	}

	default ETypeBoolean between(LocalDateTime left, Expr<LocalDateTime> right) {
		return between(Sql.val(left), right);
	}

	default ETypeBoolean between(LocalDateTime left, LocalDateTime right) {
		return between(Sql.val(left), Sql.val(right));
	}
}
