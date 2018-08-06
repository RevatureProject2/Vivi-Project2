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
		List<request> pd_req  = reimburseService.getService().viewRequest("pending");
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
		List<request> pd_req  = reimburseService.getService().viewRequest("approved");
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
	public static void updateInfo(HttpServletRequest req, HttpServletResponse res) {
		
		
	}
}
