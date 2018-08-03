package com.revature.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

/*	@Override
	public employee login(employee emp) {
		InputStream in = null;
		if(emp.isAdmin() == 1)
		{
			Properties props = new Properties();		
			try {
			in = new FileInputStream("/ERS/src/main/resources/jdbcConnection.properties");
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
						else
							return new employee();
						
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
					else
						return new employee();
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
*/

	@Override
	public employee select(employee emp) {
		try(Connection connection = jdbcConnection.getConnection()) {
			int statementIndex = 0;
			String command = "SELECT * FROM METADATA WHERE USERNAME = ?";
			PreparedStatement statement = connection.prepareStatement(command);
			statement.setString(++statementIndex, emp.getUsername());
			ResultSet result = statement.executeQuery();

			while(result.next()) {
				
				return new employee(
						result.getString("username"),
						result.getString("password")
						);
			}
		} catch (SQLException e) {
			logUtil.log.warn("Exception selecting an employee", e);
		}
			return new employee();
	}
	
	public String getCustomerHash(employee emp) {
		try(Connection connection = jdbcConnection.getConnection()) {
			int statementIndex = 0;
			String command = "SELECT GET_CUSTOMER_HASH(?,?) AS HASH FROM DUAL";
			PreparedStatement statement = connection.prepareStatement(command);
			statement.setString(++statementIndex, emp.getUsername());
			statement.setString(++statementIndex, emp.getPassword());
			ResultSet result = statement.executeQuery();

			if(result.next()) {
				return result.getString("HASH");
			}
		} catch (SQLException e) {
			logUtil.log.warn("getHash exception", e);
		} 
		return new String();
	}


}
