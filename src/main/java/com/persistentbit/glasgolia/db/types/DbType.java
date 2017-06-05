package com.persistentbit.glasgolia.db.types;

import com.persistentbit.core.collections.PByteList;
import com.persistentbit.sql.PersistSqlException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A DbDataType instance represent a database type like postgres, mysql, h2,...<br>
 * A DbDataType contains database type specific mappings for working with Sql.<br>
 * @author Peter Muys
 * @since 19/07/2016
 */
public interface DbType{


	/**
	 * Register the jdbc driver class for this database<br>
	 */
	void registerDriver();

	String getDatabaseName();

	String sqlWithLimit(long limit, String sql);

	String sqlWithLimitAndOffset(long limit, long offset, String sql);

	default String numberToString(String number, int charCount) {
		return "CAST(" + number + " AS VARCHAR(" + +charCount + ")";
	}

	default String concatStrings(String s1, String s2) {
		return "CONCAT(" + s1 + ", " + s2 + ")";
	}

	default String asLiteralString(String value) {
		if(value == null) {
			return null;
		}
		StringBuilder res = new StringBuilder();
		for(int t = 0; t < value.length(); t++) {
			char c = value.charAt(t);
			if(c == '\'') {
				res.append("\'\'");
			}
			else if(c == '\"') {
				res.append("\"\"");
			}
			else {
				res.append(c);
			}
		}
		return "\'" + res + "\'";
	}

	default String asLiteralDate(LocalDate date) {
		return "DATE '" + DateTimeFormatter.ofPattern("yyyy-MM-dd").format(date) + "'";
	}

	default String asLiteralDateTime(LocalDateTime dateTime) {
		return "TIMESTAMP '" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.nnnnnnnnnn").format(dateTime) + "'";
	}

	default String asLiteralBlob(PByteList byteList) {
		throw new PersistSqlException("Can't convert a BLOB to a literal for " + getDatabaseName());
	}

	default String toUpperCase(String value) {
		return "UCASE(" + value + ")";
	}

	default String toLowerCase(String value) {
		return "LCASE(" + value + ")";
	}

	/**
	 * Create a Sql statement that set the current Schema for a connection
	 * @param schema The schema name
	 * @return The SQL statement (without ';')
	 */
	String setCurrentSchemaStatement(String schema);



}
