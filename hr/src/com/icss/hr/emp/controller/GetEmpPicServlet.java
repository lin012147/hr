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
 * ͨ���û�������Ա��ͷ��base64����
 */
@WebServlet("/GetEmpPicServlet")
public class GetEmpPicServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		
		//��õ�ǰ�û����û���
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");
		
		//����ҵ����
		EmpService service = new EmpService();
		
		try {
			//��Ӧbase64ͷ�����ݵ�ǰ��
			String empPic = service.queryEmpPic(empLoginName);			
			out.print(empPic);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
				
	}

}