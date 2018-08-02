package com.revature.dao;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.revature.jdbcConnection.jdbcConnection;
import com.revature.model.employee;
import com.revature.util.logUtil;


public class employeeDao implements ERS_DAO {

	public static Connection conn = jdbcConnection.getConnection();
	private static employeeDao employeeDao;
	
	public static employeeDao getEmployeeDao() {
		if(employeeDao == null) {
			employeeDao = new employeeDao();
		}
		return employeeDao;
	}

	@Override
	public employee login(employee emp) {
		InputStream in = null;
		if(emp.isAdmin() == 1)
		{
			Properties props = new Properties();		
			try {
			in = new FileInputStream("src/main/resources/connections.properties");
			props.load(in);
			} catch (IOException e1) {
				
			e1.printStackTrace();
			}

			String username_input =props.getProperty("superUsername");
			String pw_input = props.getProperty("superPassword");
		
			try {
					if (emp.getUsername().equals(username_input) 
							&& emp.getPassword().equals(pw_input))
					{
						String sql = "call login(?, ?, ?)";
						
						CallableStatement cs = conn.prepareCall(sql);

						cs.setInt(1, emp.isLogin());
						cs.setString(2, emp.getUsername());
						cs.registerOutParameter(3, java.sql.Types.INTEGER);

						cs.executeQuery();
						if(cs.getInt(3) == 1)
						{
							emp.setLogin(1);
							return new employee(emp.getUsername(), emp.getPassword());
						}
						
					}
					else
						throw new InvalidInputException("Wrong username or password.");
				}
			catch(Exception e) {
				System.out.println(e.getMessage());
				}
		}
		else
		{
			try {
				String sql = "Select password from metadata where username= ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, emp.getUsername());
				ResultSet rs = ps.executeQuery();
							
				String pw = new String();
				
				while(rs.next()) {
					pw = rs.getString("password");
				}				
			
				if (emp.getPassword().equals(pw))
				{
					String sql1 = "call login(?, ?, ?)";
					
					CallableStatement cs = conn.prepareCall(sql1);

					cs.setInt(1, emp.isLogin());
					cs.setString(2, emp.getUsername());
					cs.registerOutParameter(3, java.sql.Types.INTEGER);
					
					cs.executeUpdate();
					
					if(cs.getInt(3) == 1)
					{
						emp.setLogin(1);
						return new employee(emp.getUsername(), emp.getPassword());
					}
				}
				else
					throw new InvalidInputException("Wrong username or password.");
			}
		catch(Exception e) {
			System.out.println(e.getMessage());
			}
		}
		return null;	
		}


	@Override
	public employee select(String username) {
		try(Connection connection = jdbcConnection.getConnection()) {
			int statementIndex = 0;
			String command = "SELECT * FROM METADATA WHERE USERNAME = ?";
			PreparedStatement statement = connection.prepareStatement(command);
			statement.setString(++statementIndex, employee.getUsername());
			ResultSet result = statement.executeQuery();

			while(result.next()) {
				
				return new employee(
						result.getString("username"),
						result.getString("")
						);
			}
		} catch (SQLException e) {
			logUtil.log.warn("Exception selecting a customer", e);
		}
		return new employee();
	}

}
