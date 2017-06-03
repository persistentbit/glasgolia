package com.persistentbit.glasgolia.jaql.expr;

import com.persistentbit.core.collections.PList;
import com.persistentbit.glasgolia.db.work.DbWork;
import com.persistentbit.glasgolia.jaql.Query;

import java.util.Optional;

/**
 * Created by petermuys on 14/10/16.
 */
public interface ETypeSelection<T> extends ETypeObject<T>, ETypeList<T>, DbWork<PList<T>>{

	@Override
	default Optional<ETypePropertyParent> getParent() {
		return Optional.empty();
	}

	@Override
	default String _getTableName() {
		return "selection";
	}


	Query getQuery();


	PList<BaseSelection<?>.SelectionProperty<?>> selections();


}
