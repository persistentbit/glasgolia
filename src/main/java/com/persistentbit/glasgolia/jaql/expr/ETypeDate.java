package com.persistentbit.glasgolia.jaql.expr;

import com.persistentbit.glasgolia.jaql.ExprRowReaderCache;
import com.persistentbit.glasgolia.jaql.RowReader;
import com.persistentbit.glasgolia.jaql.expr.mixins.MixinEq;

import java.time.LocalDate;

/**
 * A {@link LocalDate} {@link Expr} type with default methods
 *
 * @author Peter Muys
 * @since  4/10/16
 */
public interface ETypeDate extends Expr<LocalDate>, MixinEq<ETypeDate>{

	@Override
	default LocalDate read(RowReader _rowReader, ExprRowReaderCache _cache) {
		return _rowReader.readNext(LocalDate.class);
	}

	default ETypeBoolean eq(LocalDate date) {
		return eq(new ExprValueDate(date));
	}

	default ETypeBoolean notEq(LocalDate date) {
		return notEq(new ExprValueDate(date));
	}


	default ETypeBoolean between(Expr<LocalDate> left, LocalDate right) {
		return between(left, Sql.val(right));
	}

	//***************************  BETWEEN
	default ETypeBoolean between(Expr<LocalDate> left, Expr<LocalDate> right) {
		return new ExprBetween<>(this, left, right);
	}

	default ETypeBoolean between(LocalDate left, Expr<LocalDate> right) {
		return between(Sql.val(left), right);
	}

	default ETypeBoolean between(LocalDate left, LocalDate right) {
		return between(Sql.val(left), Sql.val(right));
	}

}
