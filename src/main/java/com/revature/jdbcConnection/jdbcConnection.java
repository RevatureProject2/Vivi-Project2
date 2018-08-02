package com.revature.jdbcConnection;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class jdbcConnection {

	private jdbcConnection() {

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = jdbcConnection.getConnection();
		
		System.out.println(conn);
		
	}
	
	public static Connection getConnection() {
		
		InputStream in = null;
		
		try {
			
			Properties props = new Properties();
			in = new FileInputStream("src/main/resources/jdbcConnection.properties");
			props.load(in);
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = null;
			
			String endpoint=props.getProperty("jdbc.url");		
			String username = props.getProperty("jdbc.username");
			String password = props.getProperty("jdbc.password");
			
	//		System.out.println(props.get("jdbc.url"));
			
			conn = DriverManager.getConnection(endpoint, username, password);
			
			return conn;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try {
			in.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
