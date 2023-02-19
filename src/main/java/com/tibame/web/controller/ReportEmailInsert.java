package com.tibame.web.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tibame.web.service.ReportEmailService;
import com.tibame.web.service.impl.ReportEmailServiceImpl;
import com.tibame.web.util.GetAuthCode;
import com.tibame.web.vo.EmailVO;
import com.tibame.web.vo.ReportImageVO;


/**
 * Servlet implementation class ReportEmailInsert
 */
@WebServlet("/report/emailInsert")
public class ReportEmailInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		resp.setContentType("application/json");

		final String authCode = GetAuthCode.genAuthCode();
		HttpSession session = req.getSession();
		session.setAttribute("authCode", authCode);
		Gson gson = new Gson();
		EmailVO emailVO = gson.fromJson(req.getReader(), EmailVO.class);
		emailVO.setAuthCode(authCode);

		
		ReportEmailService service = new ReportEmailServiceImpl();
		final String resultStr= service.insertEamil(emailVO);

					
			JsonObject respbody = new JsonObject();
			respbody.addProperty("successful", resultStr.equals("文字新增成功"));
			respbody.addProperty("message", resultStr);
			resp.getWriter().append(respbody.toString());		
		

	}

}
