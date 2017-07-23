package com.persistentbit.glasgolia.jaql.codegen.posgresql;

import com.persistentbit.core.Nullable;
import com.persistentbit.core.javacodegen.JField;
import com.persistentbit.core.javacodegen.annotations.CaseClass;
import com.persistentbit.glasgolia.db.dbdef.DbMetaColumn;
import com.persistentbit.core.utils.UString;
import com.persistentbit.core.javacodegen.annotations.NoBuilder;
import java.util.Objects;
import com.persistentbit.core.utils.UReflect;
import com.persistentbit.core.javacodegen.annotations.Generated;

/**
 * TODOC
 * 
 * @author petermuys
 * @since 15/07/17
 */
@CaseClass
@NoBuilder
public class DbJavaFieldPrimitiveType implements DbJavaField {
	private  final	DbMetaColumn	column;
	private  final	String	fieldName;
	private  final	Class	primitiveType;
	
	
	@Generated
	public DbJavaFieldPrimitiveType(DbMetaColumn column, String fieldName, Class primitiveType){
			this.column = Objects.requireNonNull(column, "column can not be null");
			this.fieldName = Objects.requireNonNull(fieldName, "fieldName can not be null");
			this.primitiveType = Objects.requireNonNull(primitiveType, "primitiveType can not be null");
	}
	@Override
	public  JField	createJField(){
	    Class cls = primitiveType;
	    if (column.getType().getIsNullable()) {
	        cls = UReflect.convertPrimitiveClassToObjectClass(cls).get();
	    }
	    JField res = new JField(fieldName, cls);
	    if (column.getType().getIsNullable()) {
	        res = res.asNullable();
	    }
	    return res;
	}
	/**
	 * Get the value of field {@link #column}.<br>
	 * @return {@link #column}
	 */
	@Generated
	public  DbMetaColumn	getColumn(){
		return this.column;
	}
	/**
	 * Create a copy of this DbJavaFieldPrimitiveType object with a new value for field {@link #column}.<br>
	 * @param column The new value for field {@link #column}
	 * @return A new instance of {@link DbJavaFieldPrimitiveType}
	 */
	@Generated
	public  DbJavaFieldPrimitiveType	withColumn(DbMetaColumn column){
		return new DbJavaFieldPrimitiveType(column, fieldName, primitiveType);
	}
	/**
	 * Get the value of field {@link #fieldName}.<br>
	 * @return {@link #fieldName}
	 */
	@Generated
	public  String	getFieldName(){
		return this.fieldName;
	}
	/**
	 * Create a copy of this DbJavaFieldPrimitiveType object with a new value for field {@link #fieldName}.<br>
	 * @param fieldName The new value for field {@link #fieldName}
	 * @return A new instance of {@link DbJavaFieldPrimitiveType}
	 */
	@Generated
	public  DbJavaFieldPrimitiveType	withFieldName(String fieldName){
		return new DbJavaFieldPrimitiveType(column, fieldName, primitiveType);
	}
	/**
	 * Get the value of field {@link #primitiveType}.<br>
	 * @return {@link #primitiveType}
	 */
	@Generated
	public  Class	getPrimitiveType(){
		return this.primitiveType;
	}
	/**
	 * Create a copy of this DbJavaFieldPrimitiveType object with a new value for field {@link #primitiveType}.<br>
	 * @param primitiveType The new value for field {@link #primitiveType}
	 * @return A new instance of {@link DbJavaFieldPrimitiveType}
	 */
	@Generated
	public  DbJavaFieldPrimitiveType	withPrimitiveType(Class primitiveType){
		return new DbJavaFieldPrimitiveType(column, fieldName, primitiveType);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbJavaFieldPrimitiveType == false) return false;
		DbJavaFieldPrimitiveType obj = (DbJavaFieldPrimitiveType)o;
		if(!column.equals(obj.column)) return false;
		if(!fieldName.equals(obj.fieldName)) return false;
		if(!primitiveType.equals(obj.primitiveType)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.column != null ? this.column.hashCode() : 0);
		result = 31 * result + (this.fieldName != null ? this.fieldName.hashCode() : 0);
		result = 31 * result + (this.primitiveType != null ? this.primitiveType.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbJavaFieldPrimitiveType[" + 
			"column=" + column + 
			", fieldName=" + (fieldName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(fieldName),32,"...") + '\"') +
			", primitiveType=" + primitiveType + 
			']';
	}
}
