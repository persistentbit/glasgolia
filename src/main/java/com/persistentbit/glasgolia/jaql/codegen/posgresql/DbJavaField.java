package com.persistentbit.glasgolia.jaql.codegen.posgresql;

import com.persistentbit.core.collections.PList;
import com.persistentbit.core.javacodegen.JField;

/**
 * TODOC
 *
 * @author petermuys
 * @since 15/07/17
 */

public interface DbJavaField{
	JField createJField();
	default PList<DbJavaFieldEnum> getUsedEnums() {
		return PList.empty();
	}
	default PList<DbJavaFieldStruct> getStructures() {
		return PList.empty();
	}
	default PList<DbJavaFieldDomain> getDomains(){
		return PList.empty();
	}
}
