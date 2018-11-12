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
				
		//设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//输出流
		PrintWriter out = response.getWriter();
		
		//获得部门id
		String deptId = request.getParameter("deptId");

		//调用业务功能
		DeptService service = new DeptService();
		
		try {
			Dept dept = service.queryDeptById(Integer.parseInt(deptId));
		
			//在request范围中存储部门数据对象
			request.setAttribute("dept", dept);	
			
			//转发到JSP视图
			request.getRequestDispatcher("/UpdateDept.jsp").forward(request, response);
						
		} catch (NumberFormatException | SQLException e) {			
			e.printStackTrace();
		}
		
	}

}