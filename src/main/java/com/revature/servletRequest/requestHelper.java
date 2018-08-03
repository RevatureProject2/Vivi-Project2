package com.revature.servletRequest;

import javax.servlet.http.HttpServletRequest;

import com.revature.controller.homeController;
import com.revature.controller.loginController;

public class requestHelper {
	public static String process(HttpServletRequest req)
	{
		String switchString = req.getRequestURI().substring(req.getContextPath().length()+1);
		while(switchString.indexOf("/")>0) {
			switchString = switchString.substring(0, switchString.indexOf("/"));
		}
		switch(switchString)
		{
		case "/ers/login.do":
			return loginController.login(req);
		case "/ers/home.do":
			return homeController.home(req);
		default:
			return "404.jsp";

		}
	}
}
