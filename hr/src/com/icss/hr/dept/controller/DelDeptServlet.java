package com.icss.hr.dept.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.dept.service.DeptService;
/**
 * ɾ������
 * @author Administrator
 *
 */

@WebServlet("/DelDeptServlet")
public class DelDeptServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//��ò���id
		String deptId = request.getParameter("deptId");
		
		//����ҵ����
		DeptService service = new DeptService();
		
		try {
			service.deleteDept(Integer.parseInt(deptId));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
