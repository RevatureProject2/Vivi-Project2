package com.revature.dao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.controller.loginController;
import com.revature.jdbcConnection.jdbcConnection;
import com.revature.model.employee;
import com.revature.model.info;
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
	
	public boolean insertRequest(request req){
		try{			
			String sql = "call insert_request(?,?,?,?,?)";
			int index = 0;

			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setString(++index, req.getStatus());
			cs.setInt(++index, req.getAmount());
			cs.setString(++index, req.getManagerID());
			cs.setString(++index, req.getUsername());
			cs.registerOutParameter(++index, java.sql.Types.INTEGER);

			cs.executeUpdate();
			
			if(cs.getInt(5) == 1)
			{
				System.out.println("Request submitted");
				return true;
			}
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return false;
	}

	public List<request> viewRequest(String status) {
		List<request> req = new ArrayList<request>();
		
		try(Connection connection = jdbcConnection.getConnection()) {
			String command = "SELECT * FROM REQUEST WHERE STATUS = ? AND USERNAME=?";
			PreparedStatement statement = connection.prepareStatement(command);
			logUtil.log.info(status);
			logUtil.log.info((String) loginController.session.getAttribute("username"));
			
			statement.setString(1, status);
			statement.setString(2, (String) loginController.session.getAttribute("username"));

			ResultSet result = statement.executeQuery();
			logUtil.log.info("here");
			while(result.next()) {
				req.add(new request(
					result.getString("status"),
					result.getInt("amount"),
					result.getString("resolvedadmin"),
					result.getString("username"),
					result.getInt("req_id")
					));
			}
		} catch (SQLException e) {
			logUtil.log.warn("Exception selecting an employee", e);
		}

		return req;
	}
	
	public info viewInfo() {
		
		try(Connection connection = jdbcConnection.getConnection()) {
			String command = "SELECT * FROM ACCOUNT WHERE USERNAME=?";
			PreparedStatement statement = connection.prepareStatement(command);
			statement.setString(1, (String) loginController.session.getAttribute("username"));

			ResultSet result = statement.executeQuery();

			while(result.next()) {
				return new info(
					result.getString("username"),
					result.getString("firstname"),
					result.getString("lastname"),
					result.getInt("balance"),
					result.getString("email")
					);
			}
		} catch (SQLException e) {
			logUtil.log.warn("Exception selecting an employee", e);
		}
		return null;

	}
	
	public boolean update(info inf) {
		try{	
			String sql = "call update_account(?,?,?,?,?)";
			
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setString(1, (String) loginController.session.getAttribute("username"));
			cs.setString(2, inf.getFirstname());
			cs.setString(3, inf.getLastname());
			cs.setString(4,  inf.getEmail());
			cs.registerOutParameter(5, java.sql.Types.INTEGER);
			cs.executeUpdate();
			
			if(cs.getInt(5) == 1)
			{
				logUtil.log.info("Account updated");
				return true;
			}
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
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
