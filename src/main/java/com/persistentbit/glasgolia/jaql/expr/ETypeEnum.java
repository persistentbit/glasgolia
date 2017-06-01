package com.persistentbit.glasgolia.jaql.expr;

import com.persistentbit.glasgolia.jaql.ExprRowReaderCache;
import com.persistentbit.glasgolia.jaql.RowReader;
import com.persistentbit.glasgolia.jaql.expr.mixins.MixinEq;

/**
 * Represents an {@link Expr} of a java enum type
 * @author Peter Muys
 * @since 5/10/16
 */
public interface ETypeEnum<T extends Enum<T>> extends Expr<T>, MixinEq<ETypeEnum<T>>{

	default ETypeBoolean eq(T value) {
		return eq(Sql.val(value));
	}

	@Override
	default T read(RowReader _rowReader, ExprRowReaderCache _cache) {
		String valueName = _rowReader.readNext(String.class);
		if(valueName == null) {
			return null;
		}
		Class<T> enumClass = _getEnumClass();
		return Enum.valueOf(enumClass, valueName);

	}

	Class<T> _getEnumClass();
}
