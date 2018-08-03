package com.revature.servletRequest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;

public class servlet extends DefaultServlet {
	private static final long serialVersionUID = 1L;
       public servlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println(request.getRequestURI());
		if (request.getRequestURI().substring(request.getContextPath().length()).startsWith("/static/"))
			super.doGet(request, response);
		else
			requestHelper.process(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
