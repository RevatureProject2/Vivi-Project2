package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.employee;
import com.revature.service.reimburseService;
import com.revature.util.logUtil;
//import com.revature.util.FinalUtil;

public class loginController {
	
	public static void login(HttpServletRequest request, HttpServletResponse response) {
		
		//If it's a GET we just return the view.
	/*	if(request.getMethod().equals(FinalUtil.HTTP_GET)) {
			return "login.jsp";
		}
		*/
		//POST logic
		String username = request.getParameter("username");
		String password = request.getParameter("password");	

		employee loggedEmployee = reimburseService.getService().login(
				new employee(username, password));
	try {
		
		if(loggedEmployee.getUsername().equals("")) {
			response.sendError(400, "Invalid Login");
			return;
		}
		else {
			request.getSession().setAttribute("loggedCustomer", loggedEmployee);
			response.sendRedirect("static/homepage.html");
//			return "/home.do";
		}
	}
	catch (Exception e)
	{
		logUtil.log.error(e.getMessage());
	}
}
}
