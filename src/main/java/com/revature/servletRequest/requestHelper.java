package com.revature.servletRequest;

import javax.servlet.http.HttpServletRequest;

import com.revature.controller.loginController;

public class requestHelper {
	public static String process(HttpServletRequest req)
	{
		switch(req.getRequestURI())
		{
		case "/ers/login.do":
			return loginController.login(req);
		default:
			return "404.jsp";

		}
	}
}
