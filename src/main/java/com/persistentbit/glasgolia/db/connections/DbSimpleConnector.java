package com.persistentbit.glasgolia.db.connections;

import com.persistentbit.core.OK;
import com.persistentbit.core.result.Result;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Objects;

/**
 * A Simple {@link DbConnector} that uses a jdbc driver to create new Connections.<br>
 * Connections are not reused (no connection pool)
 *
 * @author Peter Muys
 * @since 13/07/2016
 * @see DbConnector
 */
public class DbSimpleConnector implements DbConnector{

	private final String url;
	private final String userName;
	private final String passWord;



	public DbSimpleConnector(String url, String userName, String password) {
		this.url = Objects.requireNonNull(url);
		this.passWord = password;
		this.userName = userName;
	}

	public static Result<OK> registerDriver(String driverClass){
		return Result.function(driverClass).code(l -> {
			Driver driver = (Driver) Class.forName(driverClass).newInstance();
			DriverManager.registerDriver(driver);
			return OK.result;
		});
	}

	public static Result<DbSimpleConnector> create(String driverClass, String url, String userName, String passWord){
		return registerDriver(driverClass)
			.map(ok -> new DbSimpleConnector(url, userName, passWord))
			.logFunction(driverClass,url,userName);
	}

	@Override
	public Result<Connection> create() {
		return Result.function().code(l -> {
			Connection c;
			if(userName != null) {
				c = DriverManager.getConnection(url, userName, passWord);
			}
			else {
				c = DriverManager.getConnection(url);
			}
			c.setAutoCommit(false);
			return Result.success(c);
		});
	}
}
