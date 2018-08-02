package com.revature.service;

import com.revature.dao.employeeDao;
import com.revature.model.employee;

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
	public employee login(employee emp)
	{
		return employeeDao.getEmployeeDao().login(emp);
	}
	

}
