package com.revature.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.employee;
import com.revature.model.info;
import com.revature.model.request;
import com.revature.service.reimburseService;
import com.revature.util.logUtil;

public class requestController {
	public static void requestPending(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException
	{
		res.setContentType("application/json");
		PrintWriter pw = res.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		List<request> pd_req  = reimburseService.getService().viewRequest("pending", req.getSession().getAttribute("username").toString());
		try {
			mapper.writeValue(pw, pd_req);
			return;
		} catch (JsonGenerationException e) {
			logUtil.log.info(e.getMessage());
			e.printStackTrace();
		} catch (JsonMappingException e) {
			logUtil.log.info(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logUtil.log.info(e.getMessage());
			e.printStackTrace();
		}		
	}
	public static void requestApproved(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException
	{
		res.setContentType("application/json");
		PrintWriter pw = res.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		List<request> pd_req  = reimburseService.getService().viewRequest("approved", req.getSession().getAttribute("username").toString());
		try {
			mapper.writeValue(pw, pd_req);
			return;
		} catch (JsonGenerationException e) {
			logUtil.log.info(e.getMessage());
			e.printStackTrace();
		} catch (JsonMappingException e) {
			logUtil.log.info(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logUtil.log.info(e.getMessage());
			e.printStackTrace();
		}		
	}
	
	public static void allRequestPending(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException
	{
		res.setContentType("application/json");
		PrintWriter pw = res.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		List<request> pd_req  = reimburseService.getService().viewAllRequest("pending");
		try {
			mapper.writeValue(pw, pd_req);
			return;
		} catch (JsonGenerationException e) {
			logUtil.log.info(e.getMessage());
			e.printStackTrace();
		} catch (JsonMappingException e) {
			logUtil.log.info(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logUtil.log.info(e.getMessage());
			e.printStackTrace();
		}		
	}
	public static void allRequestApproved(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException
	{
		res.setContentType("application/json");
		PrintWriter pw = res.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		List<request> pd_req  = reimburseService.getService().viewAllRequest("approved");
		try {
			mapper.writeValue(pw, pd_req);
			return;
		} catch (JsonGenerationException e) {
			logUtil.log.info(e.getMessage());
			e.printStackTrace();
		} catch (JsonMappingException e) {
			logUtil.log.info(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logUtil.log.info(e.getMessage());
			e.printStackTrace();
		}		
	}

	public static void viewInfo(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException {
		
		res.setContentType("application/json");
		PrintWriter pw = res.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		info inf = reimburseService.getService().viewInfo();
		
		mapper.writeValue(pw, inf);
	}
	public static boolean updateInfo(HttpServletRequest req, HttpServletResponse res) throws IOException {

		String first = req.getParameter("firstname");
		String last = req.getParameter("lastname");	
		String email = req.getParameter("email");	
		
		info inf = new info(first, last, email);
		
		if(reimburseService.getService().update(inf)) {
			res.sendRedirect("static/homepage.html");
		}
		return false;
	}
	public static void allInfo(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json");
		PrintWriter pw = res.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		List<info> inf = reimburseService.getService().viewAllInfo();
		
		mapper.writeValue(pw, inf);
		
	}
	public static void resolve(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String status = req.getParameter("radio");
		int req_id = Integer.parseInt(req.getParameter("req-id"));
		if(reimburseService.getService().resolve(status,req_id, req.getSession().getAttribute("username").toString())) {
			res.sendRedirect("static/manhomepage.html");
		}
		
	}
	}
