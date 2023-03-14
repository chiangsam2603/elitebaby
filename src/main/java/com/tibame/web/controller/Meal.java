package com.tibame.web.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tibame.web.service.MealService;
import com.tibame.web.service.impl.MealServiceImpl;
import com.tibame.web.vo.MealVO;

import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class MealSearch
 */
@WebServlet("/Meal")
public class Meal extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setCharacterEncoding("UTF-8");
		String str = request.getParameter("name");
//		String userId = request.getParameter("userid");
		if (str.equals("getall")) {
			Gson gson = new Gson();
//			String cartCount = "";
//			if (userId != null || userId != "") {
//				Jedis jedis = new Jedis("localhost", 6379);
//				cartCount = String.valueOf(jedis.keys("user" + userId + "*").size());
//				jedis.close();
//			}
			MealService service = new MealServiceImpl();
			List<MealVO> list = service.getAllMeal();
//			System.out.println(list);

			if (list.size() == 0) {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			} else {
//				JsonObject respbody = new JsonObject();
//				respbody.addProperty("cartcount", cartCount);
				response.setContentType("application/json");
				response.getWriter().write(gson.toJson(list));
//				response.getWriter().write(respbody.toString());
			}
		}

		if (str.equals("insert")) {
			Gson gson = new Gson();
			MealVO jsonObject = gson.fromJson(request.getReader(), MealVO.class);
			byte[] mealpic = Base64.getDecoder().decode(jsonObject.getBase64());
			jsonObject.setMealPic(mealpic);
			MealService service = new MealServiceImpl();
			int udm = service.insertMeal(jsonObject);
//			System.out.println(udm);
			JsonObject respbody = new JsonObject();
			if (udm > 0) {
				respbody.addProperty("msg", "success");
				response.setContentType("application/json");
				response.getWriter().write(respbody.toString());
			} else {
				respbody.addProperty("msg", "fail");
				response.setContentType("application/json");
				response.getWriter().write(respbody.toString());
			}
		}

		if (str.equals("update")) {
			Gson gson = new Gson();
			MealVO jsonObject = gson.fromJson(request.getReader(), MealVO.class);
			System.out.println(jsonObject.getBase64());
			if (jsonObject.getBase64() == "" || jsonObject.getBase64() == null) {
				jsonObject.setMealPic(null);
			} else {
				byte[] mealpic = Base64.getMimeDecoder().decode(jsonObject.getBase64());
				jsonObject.setMealPic(mealpic);
			}
			MealService service = new MealServiceImpl();
			int udm = service.updateMeal(jsonObject);
			JsonObject respbody = new JsonObject();
			if (udm > 0) {
				respbody.addProperty("msg", "success");
				response.setContentType("application/json");
				response.getWriter().write(respbody.toString());
			} else {
				respbody.addProperty("msg", "fail");
				response.setContentType("application/json");
				response.getWriter().write(respbody.toString());
			}
		}

		if (str.equals("findByPrimaryKey")) {
			Gson gson = new Gson();
			response.setContentType("application/json");
			MealVO jsonObject = gson.fromJson(request.getReader(), MealVO.class);
			System.out.println(jsonObject.getMealId());
			MealService service = new MealServiceImpl();
			MealVO meal = service.findByPrimaryKey(jsonObject.getMealId());
			response.getWriter().write(gson.toJson(meal));
		}

		if (str.equals("delete")) {
			Gson gson = new Gson();
			response.setContentType("application/json");
			MealVO jsonObject = gson.fromJson(request.getReader(), MealVO.class);
			System.out.println(jsonObject.getMealId());
			MealService service = new MealServiceImpl();
			int udm = service.deleteMeal(jsonObject.getMealId());
			JsonObject respbody = new JsonObject();
			if (udm > 0) {
				respbody.addProperty("msg", "success");
				response.setContentType("application/json");
				response.getWriter().write(respbody.toString());
			} else {
				respbody.addProperty("msg", "fail");
				response.setContentType("application/json");
				response.getWriter().write(respbody.toString());
			}
		}
	}

	public Meal() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

}
