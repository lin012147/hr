package com.icss.hr.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.emp.service.EmpService;

/**
 * 查询指定ID的员工
 */
@WebServlet("/GetEmpServlet")
public class GetEmpServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		//获得员工id
		String  empId =  request.getParameter("empId");
		
		//调用业务功能
		EmpService service = new EmpService();
		
		try {
			Emp emp = service.queryEmpById(Integer.parseInt(empId));
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			
			String json = gson.toJson(emp);
			
			out.print(json);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
