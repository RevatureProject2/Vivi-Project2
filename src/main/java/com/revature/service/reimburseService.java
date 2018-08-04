package com.revature.service;

import com.revature.dao.employeeDao;
import com.revature.model.employee;
import com.revature.model.request;

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
	public request submit(request req) {
		
		return null;
	}
	
	
	
	
	
	
	

}
