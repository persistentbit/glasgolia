package com.persistentbit.glasgolia.jaql.customtypes;

import com.persistentbit.core.Nullable;
import com.persistentbit.core.javacodegen.annotations.CaseClass;
import com.persistentbit.core.javacodegen.annotations.Generated;
import com.persistentbit.core.javacodegen.annotations.NoBuilder;
import com.persistentbit.core.javacodegen.annotations.NoWith;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * TODOC
 * 
 * @author petermuys
 * @since 14/07/17
 */
@CaseClass
@NoWith
@NoBuilder
public class Money {
	private  final	BigDecimal	value;
	
	
	@Generated
	public Money(BigDecimal value){
			this.value = Objects.requireNonNull(value, "value can not be null");
	}
	/**
	 * Get the value of field {@link #value}.<br>
	 * @return {@link #value}
	 */
	@Generated
	public  BigDecimal	getValue(){
		return this.value;
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof Money == false) return false;
		Money obj = (Money)o;
		if(!value.equals(obj.value)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.value != null ? this.value.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "Money[" + 
			"value=" + value + 
			']';
	}
}
