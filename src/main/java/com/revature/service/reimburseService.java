package com.revature.service;
import java.util.List;

import com.revature.dao.employeeDao;
import com.revature.model.employee;
import com.revature.model.info;
import com.revature.model.request;
import com.revature.util.logUtil;

public class reimburseService {
	private static reimburseService service;
	private reimburseService() {
	}
	public static reimburseService getService() {
		if(service == null) {
			service = new reimburseService();
		}
		return service;
	}
	public employee login(employee emp) {
		
		employee loggedEmp = employeeDao.getEmployeeDao().select(emp);
		if(loggedEmp.getPassword().equals(employeeDao.getEmployeeDao().getCustomerHash(emp)))
			return loggedEmp;
		return new employee();
	}
	
	public employee login_manager(employee emp) {
		
		employee loggedEmp = employeeDao.getEmployeeDao().select(emp);
		System.out.println(loggedEmp.isAdmin());
		if(loggedEmp.isAdmin() == 0)
			return new employee();
		else {
			if(loggedEmp.getPassword().equals(employeeDao.getEmployeeDao().getCustomerHash(emp)))
				return loggedEmp;
		}
		return new employee();
	}
	

	public boolean submit(request req) {
		boolean re = employeeDao.getEmployeeDao().insertRequest(req);
		return re;
	}
	
	public List<request> viewRequest(String status){
		return employeeDao.getEmployeeDao().viewRequest(status);
	}
	
	public List<request> viewAllRequest(String status){
		return employeeDao.getEmployeeDao().viewAll(status);
	}

	
	public info viewInfo() {
		return employeeDao.getEmployeeDao().viewInfo();
	}
	
	
	public boolean update(info inf) {
		return employeeDao.getEmployeeDao().update(inf);
	}
	public List<info> viewAllInfo() {
		return employeeDao.getEmployeeDao().viewAllInfo();
	}
	
	
	
	
	
	
	
	

}
