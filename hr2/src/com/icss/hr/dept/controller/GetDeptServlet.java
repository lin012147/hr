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
 * ����id��ѯ�������ݣ�ת��Ϊjson��Ӧ��ǰ��
 */
@WebServlet("/GetDeptServlet")
public class GetDeptServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		//���ñ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//�����
		PrintWriter out = response.getWriter();
		
		//��ò���id
		String deptId = request.getParameter("deptId");

		//����ҵ����
		DeptService service = new DeptService();
		
		try {
			Dept dept = service.queryDeptById(Integer.parseInt(deptId));
		
			//��request��Χ�д洢�������ݶ���
			request.setAttribute("dept", dept);	
			
			//ת����JSP��ͼ
			request.getRequestDispatcher("/UpdateDept.jsp").forward(request, response);
						
		} catch (NumberFormatException | SQLException e) {			
			e.printStackTrace();
		}
		
	}

}