package com.revature.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controller.homeController;
import com.revature.service.reimburseService;

public class ajaxHelper {
		public static Object process(HttpServletRequest request, HttpServletResponse response) {
			switch(request.getRequestURI()) {
			case "pending":
				 reimburseService.getService().viewRequest("pending");
			case "approved":
				return reimburseService.getService().viewRequest("approved");
			case "view":
				
			default:
			}
			return null;
	}

}
