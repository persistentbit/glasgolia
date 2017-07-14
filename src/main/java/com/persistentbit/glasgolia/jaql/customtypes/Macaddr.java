package com.persistentbit.glasgolia.jaql.customtypes;

import com.persistentbit.core.Nullable;
import com.persistentbit.core.javacodegen.annotations.NoWith;
import com.persistentbit.core.javacodegen.annotations.CaseClass;
import com.persistentbit.core.utils.UString;
import com.persistentbit.core.javacodegen.annotations.NoBuilder;
import java.util.Objects;
import com.persistentbit.core.javacodegen.annotations.Generated;

/**
 * TODOC
 * 
 * @author petermuys
 * @since 14/07/17
 */
@CaseClass
@NoBuilder
@NoWith
public class Macaddr {
	private  final	String	value;
	
	
	@Generated
	public Macaddr(String value){
			this.value = Objects.requireNonNull(value, "value can not be null");
	}
	/**
	 * Get the value of field {@link #value}.<br>
	 * @return {@link #value}
	 */
	@Generated
	public  String	getValue(){
		return this.value;
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof Macaddr == false) return false;
		Macaddr obj = (Macaddr)o;
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
		return "Macaddr[" + 
			"value=" + (value == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(value),32,"...") + '\"') +
			']';
	}
}
