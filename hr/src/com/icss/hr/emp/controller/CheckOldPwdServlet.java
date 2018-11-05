package com.icss.hr.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.hr.emp.service.EmpService;

/**
 * Servlet implementation class CheckOldPwdServlet
 */
@WebServlet("/CheckOldPwdServlet")
public class CheckOldPwdServlet extends HttpServlet {
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得输出流
		PrintWriter out = response.getWriter();
		//获得旧密码
		String empOldPwd = request.getParameter("empOldPwd");
		//获得用户名，根据用户名查询密码
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
		
		EmpService service = new EmpService();
		
		try {
			String empPwd = service.queryEmpPwd(empLoginName);
			//判断是否一致
			
			if(empPwd.equals(empOldPwd)){
				System.out.println(empPwd);
				System.out.println(empOldPwd);
				out.print(true);
			}else{
				out.print(false);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
