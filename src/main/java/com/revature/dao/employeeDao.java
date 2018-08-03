package com.revature.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.revature.jdbcConnection.jdbcConnection;
import com.revature.model.employee;
import com.revature.model.request;
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
	
	public boolean insertRequest(employee emp, request req){
		try(Connection connection = jdbcConnection.getConnection()){
			int index = 0;
			String sql = "INSERT INTO REQUEST VALUES(?,?,?,?)";
			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setInt(++index, emp.getAccountID());
			stm.setString(++index, req.getStatus());
			stm.setInt(++index, req.getAmount());
			stm.setInt(++index, req.getManagerID());
		}catch (SQLException e) {
			logUtil.log.warn("Exception inserting request");
		}
		return false;
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
