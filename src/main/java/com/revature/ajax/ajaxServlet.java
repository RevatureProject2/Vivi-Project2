package com.revature.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/* Ajax front controller that handles only AJAX requests */
public class ajaxServlet extends HttpServlet {

	private static final long serialVersionUID = 590444879370100299L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		response.getWriter().write(
				new ObjectMapper().writeValueAsString(ajaxHelper.process(request, response)));
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		response.getWriter().write(
				new ObjectMapper().writeValueAsString(ajaxHelper.process(request, response)));
	}
}
