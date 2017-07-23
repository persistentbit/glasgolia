package com.persistentbit.glasgolia.jaql.codegen;

import com.persistentbit.core.Nullable;
import com.persistentbit.core.function.ThrowingFunction;
import com.persistentbit.core.javacodegen.annotations.CaseClass;
import com.persistentbit.core.javacodegen.annotations.DefaultValue;
import com.persistentbit.core.javacodegen.annotations.Generated;
import com.persistentbit.core.result.Result;
import com.persistentbit.core.utils.UString;
import com.persistentbit.core.utils.builders.NOT;
import com.persistentbit.core.utils.builders.SET;
import com.persistentbit.glasgolia.jaql.codegen.posgresql.DbNameTransformer;
import com.persistentbit.glasgolia.jaql.codegen.posgresql.JavaGenTableSelection;

import java.util.Objects;
import java.util.function.Function;

/**
 * TODOC
 * 
 * @author petermuys
 * @since 23/07/17
 */
@CaseClass
public class DbJavaGenOptions {
	private  final	JavaGenTableSelection	selection;
	@DefaultValue("new DbNameTransformer(name -> UString.firstUpperCase(UString.snake_toCamelCase(name)))")
	private  final DbNameTransformer nameTransformer;
	private  final	String	rootPackage;
	
	
	@Generated
	public DbJavaGenOptions(JavaGenTableSelection selection, @Nullable DbNameTransformer nameTransformer, String rootPackage){
			this.selection = Objects.requireNonNull(selection, "selection can not be null");
			this.nameTransformer = nameTransformer == null ? new DbNameTransformer(name -> UString.firstUpperCase(UString.snake_toCamelCase(name))) : nameTransformer;
			this.rootPackage = Objects.requireNonNull(rootPackage, "rootPackage can not be null");
	}
	@Generated
	public DbJavaGenOptions(JavaGenTableSelection selection, String rootPackage){
			this(selection, null, rootPackage);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2> {
		private	JavaGenTableSelection	selection;
		private	DbNameTransformer	nameTransformer;
		private	String	rootPackage;
		
		
		public  Builder<SET, _T2>	setSelection(JavaGenTableSelection selection){
			this.selection	=	selection;
			return (Builder<SET, _T2>)this;
		}
		public  Builder<_T1, _T2>	setNameTransformer(@Nullable DbNameTransformer nameTransformer){
			this.nameTransformer	=	nameTransformer;
			return this;
		}
		public  Builder<_T1, SET>	setRootPackage(String rootPackage){
			this.rootPackage	=	rootPackage;
			return (Builder<_T1, SET>)this;
		}
	}
	/**
	 * Get the value of field {@link #selection}.<br>
	 * @return {@link #selection}
	 */
	@Generated
	public  JavaGenTableSelection	getSelection(){
		return this.selection;
	}
	/**
	 * Create a copy of this DbJavaGenOptions object with a new value for field {@link #selection}.<br>
	 * @param selection The new value for field {@link #selection}
	 * @return A new instance of {@link DbJavaGenOptions}
	 */
	@Generated
	public  DbJavaGenOptions	withSelection(JavaGenTableSelection selection){
		return new DbJavaGenOptions(selection, nameTransformer, rootPackage);
	}
	/**
	 * Get the value of field {@link #nameTransformer}.<br>
	 * @return {@link #nameTransformer}
	 */
	@Generated
	public  DbNameTransformer	getNameTransformer(){
		return this.nameTransformer;
	}
	/**
	 * Create a copy of this DbJavaGenOptions object with a new value for field {@link #nameTransformer}.<br>
	 * @param nameTransformer The new value for field {@link #nameTransformer}
	 * @return A new instance of {@link DbJavaGenOptions}
	 */
	@Generated
	public  DbJavaGenOptions	withNameTransformer(@Nullable DbNameTransformer nameTransformer){
		return new DbJavaGenOptions(selection, nameTransformer, rootPackage);
	}
	/**
	 * Get the value of field {@link #rootPackage}.<br>
	 * @return {@link #rootPackage}
	 */
	@Generated
	public  String	getRootPackage(){
		return this.rootPackage;
	}
	/**
	 * Create a copy of this DbJavaGenOptions object with a new value for field {@link #rootPackage}.<br>
	 * @param rootPackage The new value for field {@link #rootPackage}
	 * @return A new instance of {@link DbJavaGenOptions}
	 */
	@Generated
	public  DbJavaGenOptions	withRootPackage(String rootPackage){
		return new DbJavaGenOptions(selection, nameTransformer, rootPackage);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbJavaGenOptions == false) return false;
		DbJavaGenOptions obj = (DbJavaGenOptions)o;
		if(!selection.equals(obj.selection)) return false;
		if(!nameTransformer.equals(obj.nameTransformer)) return false;
		if(!rootPackage.equals(obj.rootPackage)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.selection != null ? this.selection.hashCode() : 0);
		result = 31 * result + (this.nameTransformer != null ? this.nameTransformer.hashCode() : 0);
		result = 31 * result + (this.rootPackage != null ? this.rootPackage.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbJavaGenOptions[" + 
			"selection=" + selection + 
			", nameTransformer=" + nameTransformer + 
			", rootPackage=" + (rootPackage == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(rootPackage),32,"...") + '\"') +
			']';
	}
	@Generated
	public  DbJavaGenOptions	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setSelection(this.selection);
		b.setNameTransformer(this.nameTransformer);
		b.setRootPackage(this.rootPackage);
		b = updater.apply(b);
		return new DbJavaGenOptions(b.selection, b.nameTransformer, b.rootPackage);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static DbJavaGenOptions	build(ThrowingFunction<Builder<NOT,NOT>, Builder<SET,SET>, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new DbJavaGenOptions(b.selection, b.nameTransformer, b.rootPackage);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Result<DbJavaGenOptions>	buildExc(ThrowingFunction<Builder<NOT,NOT>, Builder<SET,SET>,Exception> setter){
		return setter.applyResult(new Builder<>()).mapExc(b -> new DbJavaGenOptions(b.selection, b.nameTransformer, b.rootPackage));
	}
}
