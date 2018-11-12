package com.icss.hr.dept.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.dept.service.DeptService;

/**
 * ���²�������
 */
@WebServlet("/UpdateDeptServlet")
public class UpdateDeptServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ���ñ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// ����������
		String deptId = request.getParameter("deptId");
		String deptName = request.getParameter("deptName");
		String deptLoc = request.getParameter("deptLoc");

		// ��װΪpojo����
		Dept dept = new Dept(Integer.parseInt(deptId), deptName, deptLoc);

		// ����ҵ����
		DeptService service = new DeptService();
		
		try {
			service.updateDept(dept);
			
			//�ض��򵽲�ѯ
			response.sendRedirect("QueryDeptServlet");
		} catch (SQLException e) {			
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
