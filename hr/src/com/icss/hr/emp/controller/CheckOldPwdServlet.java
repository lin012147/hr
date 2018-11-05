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
		//��������
		PrintWriter out = response.getWriter();
		//��þ�����
		String empOldPwd = request.getParameter("empOldPwd");
		//����û����������û�����ѯ����
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
		
		EmpService service = new EmpService();
		
		try {
			String empPwd = service.queryEmpPwd(empLoginName);
			//�ж��Ƿ�һ��
			
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
