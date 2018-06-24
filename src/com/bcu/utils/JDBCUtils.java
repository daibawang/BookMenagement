package com.bcu.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
 * @功能介绍： 1. 注册驱动
 *  		  2. 连接数据库（获取连接）
 *            3. 关闭资源
 * 
 * */
public class JDBCUtils {
	private static Properties properties;
	
	static{
		
		try {
			//获取到类加载器
			ClassLoader cl = JDBCUtils.class.getClassLoader();
			InputStream is = cl.getResourceAsStream("db.properties");
			//Properties类可以帮我们加载properties文件中的信息
			properties = new Properties();
			//加载完以后，properties对象中就会有文件中所有内容
			properties.load(is);
			//根据key获取对应的值
			String driver = properties.getProperty("driver");
			Class.forName(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//获取连接的方法  properties文件 配置文件
	
	public static Connection getConnection(){
		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		try {
			Connection connection = DriverManager.getConnection(url,username,password);
			return connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	//释放资源
		public static void release(ResultSet set,Statement statement,Connection connection) {
			//释放资源的操作，一定要放在finally中
			if(statement != null){
				try {
					statement.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			if(set != null){
				try {
					set.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
}
