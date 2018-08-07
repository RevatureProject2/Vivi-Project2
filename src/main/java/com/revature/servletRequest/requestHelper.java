package com.revature.servletRequest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controller.homeController;
import com.revature.controller.loginController;
import com.revature.controller.requestController;

public class requestHelper {
	public static void process(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
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
		case "pending":
			requestController.requestPending(req, res);
			break;
		case "approved":
			requestController.requestApproved(req, res);
			break;
		case "info":
			requestController.viewInfo(req, res);
		case "update":
			requestController.updateInfo(req, res);
		default:
		//	return "404.jsp";

		}
	}
}
