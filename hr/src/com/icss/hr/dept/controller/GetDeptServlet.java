package com.icss.hr.dept.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.dept.service.DeptService;

/**
 * 根据id查询部门数据，转换为json响应到前端
 */
@WebServlet("/GetDeptServlet")
public class GetDeptServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		String  deptId = request.getParameter("deptId");
		
		DeptService service = new DeptService();
		
		
		
	 	try {
			Dept dept =service.quereyDeptById(Integer.parseInt(deptId));
			
			Gson gson = new Gson();
			
			String jsonstr = gson.toJson(dept);
			
			out.print(jsonstr);
			 
		} catch (NumberFormatException | SQLException e) {
			
			e.printStackTrace();
		}
	 	
	 	
		
	}

}