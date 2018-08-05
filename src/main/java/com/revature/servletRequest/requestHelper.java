package com.revature.servletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controller.homeController;
import com.revature.controller.loginController;

public class requestHelper {
	public static void process(HttpServletRequest req, HttpServletResponse res)
	{
		String switchString = req.getRequestURI().substring(req.getContextPath().length()+1);
		while(switchString.indexOf("/")>0) {
			switchString = switchString.substring(0, switchString.indexOf("/"));
		}
		switch(switchString)
		{
		case "login":
			loginController.login(req, res);
			break;
		case "home":
			homeController.home(req);
			break;
		case "logout":
			loginController.logout(req, res);
			break;
		case "submit":
			homeController.requestSubmit(req, res);
			break;
		
			
		default:
		//	return "404.jsp";

		}
	}
}
