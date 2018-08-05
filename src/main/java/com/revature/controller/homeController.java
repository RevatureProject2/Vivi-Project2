package com.revature.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.request;
import com.revature.service.reimburseService;
import com.revature.util.logUtil;

public class homeController {

	public static String home(HttpServletRequest req) {
		
		return "homepage.html";
	}
	public static void requestSubmit(HttpServletRequest req, HttpServletResponse res) {
		int amount = Integer.parseInt(req.getParameter("amount"));
		if(reimburseService.getService().submit(new request(loginController.username, amount)))
			try {
				res.getWriter().append("Request successfully sent to the system");
			} catch (IOException e) {
				logUtil.log.warn("IO Exception throws when sending request");
			}
		else
			try {
				res.getWriter().append("Request failed to send. Please try again");
			} catch (IOException e) {
				logUtil.log.error("IO Exception throws when sending request");
				e.printStackTrace();
			}
	}
	
	

	
	
	
}
