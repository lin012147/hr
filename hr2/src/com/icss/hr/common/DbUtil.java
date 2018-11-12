package com.icss.hr.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import oracle.jdbc.driver.OracleDriver;

/**
 * 数据库工具类
 * @author Administrator
 */
public class DbUtil {
	
	static {		
		try {
			DriverManager.registerDriver(new OracleDriver());
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
	}
	
	/**
	 * 返回数据库连接对象 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "myhr";
		String password = "myhr";
		
		Connection conn = DriverManager.getConnection(url, user, password);
		
		return conn;
	}
	
}