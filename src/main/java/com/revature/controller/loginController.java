package com.revature.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.employee;
import com.revature.service.reimburseService;
import com.revature.util.logUtil;
//import com.revature.util.FinalUtil;

public class loginController {
	public static String username="";
	public static void login(HttpServletRequest request, HttpServletResponse response) {
		username = request.getParameter("username");
		String password = request.getParameter("password");	

		employee loggedEmployee = reimburseService.getService().login(
				new employee(username, password));
	try {
		if(loggedEmployee.getUsername().equals("")) {
			response.sendRedirect("static/login.html");
		}
		else {
			request.getSession().setAttribute("loggedCustomer", loggedEmployee);
			response.sendRedirect("static/homepage.html");
		}
	}
	catch (Exception e)
	{
		logUtil.log.error(e.getMessage());
	}
}
	public static void logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		try {
			response.getWriter().append("http://localhost:8080/ers/static/login.html");
		} catch (IOException e) {
			logUtil.log.warn("IO Exception when logging out");
		}
	}
}
