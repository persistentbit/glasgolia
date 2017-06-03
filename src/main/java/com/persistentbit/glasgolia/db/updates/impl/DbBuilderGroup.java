package com.persistentbit.glasgolia.db.updates.impl;


import com.persistentbit.core.OK;
import com.persistentbit.core.collections.PList;
import com.persistentbit.core.collections.PStream;
import com.persistentbit.core.result.Result;
import com.persistentbit.glasgolia.db.updates.DbBuilder;
import com.persistentbit.glasgolia.db.work.DbWork;

/**
 * @author Peter Muys
 * @since 18/06/16
 */
public class DbBuilderGroup implements DbBuilder{

	private final PList<DbBuilder> builders;

	public DbBuilderGroup(DbBuilder... builders) {
		this.builders = PStream.from(builders).plist();
	}


	@Override
	public DbWork<OK> buildOrUpdate() {
		return DbWork.sequence(builders.map(DbBuilder::buildOrUpdate));
	}

	@Override
	public DbWork<OK> dropAll() {
		return DbWork.sequence(builders.map(DbBuilder::dropAll));
	}

	@Override
	public DbWork<Boolean> hasUpdatesThatAreDone() {
		return DbWork.function().code(log -> ctx -> {
			boolean ok = true;
			for(DbBuilder b : builders) {
				Result<Boolean> itemOk = b.hasUpdatesThatAreDone().execute(ctx);
				if(itemOk.isError()) {
					return itemOk;
				}
				ok = ok && itemOk.orElseThrow();
				log.add(itemOk);
			}
			return Result.success(ok);
		});
	}
}
