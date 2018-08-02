package com.revature.dao;

import com.revature.model.employee;

public interface ERS_DAO {
	public employee login(employee emp);
	public employee select(String username);
}
