package com.revature.jdbcConnection;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.revature.util.logUtil;

public class jdbcConnection {

	private jdbcConnection() {

	}	
	public static Connection getConnection() {
		
		InputStream in = null;
		Properties props = new Properties();

		try {
			in = new FileInputStream("C:\\Users\\Vivi Vo\\Desktop\\STS\\ERS\\src\\main\\resources\\jdbcConnection.properties");
			props.load(in);
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(Exception e)
		{
			logUtil.log.warn("exception thrown whhen loading oracle driver");
		}
		

			Connection conn = null;
			
			String endpoint=props.getProperty("jdbc.url");		
			String username = props.getProperty("jdbc.username");
			String password = props.getProperty("jdbc.password");
						
			try {
				conn = DriverManager.getConnection(endpoint, username, password);
			} catch (SQLException e1) {
				logUtil.log.warn("sql exception thrown when loading database");
			}
			
			return conn;
		
	}

}
