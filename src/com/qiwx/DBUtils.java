package com.qiwx;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author qiwx
 * @time 2017年10月30日 上午9:58:53
 * @Des 数据库工具类
 **/
public class DBUtils {
	String driver;
	String url;
	String username;
	String password;
	Connection connection;
	// 连接数据库
	public Connection getConnection() {
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader()
					.getResourceAsStream("DBConfig.properties"));
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			Class.forName(driver);
			System.out.println("Success loading Mysql Driver!");
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//关闭数据库
	public void closeDB(Connection connection){
		if(connection!=null){
			try {
				connection.close();	
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
