package com.icss.hr.common;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.impl.NewPooledConnection;
/**
 * 数据库工具类
 * @author Administrator
 */
public class Dbutil {
	
	//创建数据源对象
	private static ComboPooledDataSource dataSource;
	
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
	
	static {		
		
		try {
			dataSource = new ComboPooledDataSource();
			
			//连接参数
			dataSource.setDriverClass("oracle.jdbc.driver.OracleDriver");//注册驱动
			dataSource.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");//jdbc连接url
			dataSource.setUser("myhr");//用户名
			dataSource.setPassword("myhr");//密码
			
			//连接池设置
			dataSource.setInitialPoolSize(10);//初始连接池
			dataSource.setMaxPoolSize(20);//最大连接数
			dataSource.setMinPoolSize(10);//最少保留的连接数
			dataSource.setMaxIdleTime(60);//空闲连接如果60秒没有被使用，就销毁
			
		} catch (Exception e) {			
			e.printStackTrace();
		}	
		
	}
	
	/**
	 * 返回数据库连接对象 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		
		//先返回线程本地变量中的连接对象
		Connection conn = threadLocal.get();
		
		//如果连接对象不存在，或者连接对象已关闭，从连接池返回一个新连接
		if (conn == null || conn.isClosed()) {
			conn = dataSource.getConnection();
			threadLocal.set(conn);
		}		
		
		//如果连接对象在当前请求线程中已存在，直接返回原来的连接对象
		return conn;
	}
	
	/**
	 * 关闭连接
	 */
	public static void close() {
		
		//先返回线程本地变量中的连接对象
		Connection conn = threadLocal.get();
				
		try {
			//如果连接对象存在，并且不是关闭状态
			if (conn != null && !conn.isClosed()) {
				conn.close();
				threadLocal.remove();
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
	}
	
	
	
	
}