package com.persistentbit.glasgolia.nativesql;

import com.persistentbit.core.collections.PList;
import com.persistentbit.core.function.ThrowingFunction;
import com.persistentbit.core.result.Result;

import java.sql.ResultSet;

/**
 * TODOC
 *
 * @author petermuys
 * @since 5/06/17
 */
public class UJdbc{

	/**
	 * Transform a {@link ResultSet} in a {@link PList}
	 * @param rs The result set
	 * @param rowMapper  The Single ResultRow mapper
	 * @param <R> The Type of the Result row
	 * @return A Result with a {@link PList} of R Records
	 */
	public static <R> Result<PList<R>> getList(ResultSet rs, ThrowingFunction<ResultSet,R,Exception> rowMapper){
		return Result.function().code(l-> {
			try{
				PList<R> result = PList.empty();
				while(rs.next()){
					result = result.plus(rowMapper.apply(rs));
				}
				return Result.success(result);
			}catch(Exception e){
				return Result.failure(e);
			}
			finally {
				rs.close();
			}
		});
	}

	/**
	 * Get Maximum 1 Record from a {@link ResultSet}.
	 * @param rs The ResultSet
	 * @param rowMapper The mapper from one row in a {@link ResultSet} to R
	 * @param <R> The Result Row Type
	 * @return The Result R, Empty if no rows are found, Failure on more than 1 row
	 */
	public static <R> Result<R> getMaxOne(ResultSet rs, ThrowingFunction<ResultSet, R, Exception> rowMapper){
		return Result.function().code(l-> {
			try{

				if(rs.next() == false){
					return Result.empty();
				}
				Result<R> result = Result.result(rowMapper.apply(rs));
				if(rs.next()){
					return Result.failure("More than 1 record in resultset");
				}
				return result;
			}catch(Exception e){
				return Result.failure(e);
			}
			finally {
				rs.close();
			}
		});
	}
}
