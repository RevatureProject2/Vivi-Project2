package com.revature.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.util.logUtil;

public class homeController {

	public static String home(HttpServletRequest req) {
		
		return "homepage.html";
	}
	public static String request(HttpServletRequest req, HttpServletResponse res) {
		int amount = Integer.parseInt(req.getParameter("amount"));
		//call service
		return null;
	}
}
