package com.revature.dao;

import com.revature.model.employee;

public interface ERS_DAO {
	public employee select(employee emp);
	public String getCustomerHash(employee emp);
}
