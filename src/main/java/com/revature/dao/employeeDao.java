package com.revature.dao;
import java.sql.CallableStatement;
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
	
	public boolean insertRequest(request req){
/*		try(Connection connection = jdbcConnection.getConnection()){
			int index = 0;
			String sql = "INSERT INTO REQUEST VALUES(?,?,?,?)";
			PreparedStatement stm = connection.prepareStatement(sql);
	//		System.out.println(req.toString());
			
			stm.setInt(++index, req.getAccountID());
			stm.setString(++index, req.getStatus());
			stm.setInt(++index, req.getAmount());
			stm.setString(++index, req.getManagerID());
			
			ResultSet rs = stm.executeQuery();

			if(stm.executeUpdate() > 0) {
				return true;
			}

		}catch (SQLException e) {
			logUtil.log.warn("Exception inserting request");
			e.printStackTrace();
		}
		return false;*/
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

	public request viewRequest() {
		return null;
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
