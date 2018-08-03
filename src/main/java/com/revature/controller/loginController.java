package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.model.employee;
import com.revature.service.reimburseService;
import com.revature.util.logUtil;
//import com.revature.util.FinalUtil;

public class loginController {
	
	public static String login(HttpServletRequest request) {
		
		//If it's a GET we just return the view.
	/*	if(request.getMethod().equals(FinalUtil.HTTP_GET)) {
			return "login.jsp";
		}
		*/
		//POST logic
		employee loggedEmployee = reimburseService.getService().login(
				new employee(
						request.getParameter("username"),
						request.getParameter("password")
						));
	try {
		
		if(loggedEmployee.getUsername().equals("")) {
			
			return "/login.do";
		}
		else {
			request.getSession().setAttribute("loggedCustomer", loggedEmployee);
			return "/home.do";
		}
	}
	catch (Exception e)
	{
		logUtil.log.error(e.getMessage());
	}
	return "error";
}
}
