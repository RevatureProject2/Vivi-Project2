package com.revature.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.employee;
import com.revature.service.reimburseService;
import com.revature.util.logUtil;
//import com.revature.util.FinalUtil;

public class loginController {
	public static HttpSession session = null;
	
	public static void login(HttpServletRequest request, HttpServletResponse response) {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");	
		
		employee loggedEmployee = reimburseService.getService().login(
				new employee(username, password));
	try {
		if(loggedEmployee.getUsername().equals("")) {
			response.sendRedirect("static/login.html");
		}
		else {
			session = request.getSession();
			session.setAttribute("loggedCustomer", loggedEmployee);
			session.setAttribute("username", username);
			
			response.sendRedirect("static/homepage.html");
		}
	}
	catch (Exception e)
	{
		logUtil.log.error(e.getMessage());
	}
}
	public static void login_manager(HttpServletRequest request, HttpServletResponse response) {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");	
		
		employee loggedEmployee = reimburseService.getService().login(
				new employee(username, password));
	try {
		if(loggedEmployee.getUsername().equals("")) {
			response.sendRedirect("static/login.html");
		
		}
		else {
			session = request.getSession();
			session.setAttribute("loggedCustomer", loggedEmployee);
			session.setAttribute("username", username);
			
			response.sendRedirect("static/manhomepage.html");
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
