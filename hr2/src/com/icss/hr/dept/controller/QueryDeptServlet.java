package com.icss.hr.dept.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.dept.service.DeptService;

/**
 * ��ѯ�����б�
 */
@WebServlet("/QueryDeptServlet")
public class QueryDeptServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//���ñ���
		response.setContentType("text/html;charset=utf-8");
		
		//��������
		PrintWriter out = response.getWriter();

		DeptService service = new DeptService();

		try {
			//����ҵ��������ݼ���
			ArrayList<Dept> list = service.queryDept();
			
			//��request��Χ����������
			request.setAttribute("list", list);
			
			//����ת����JSP��ͼ
			request.getRequestDispatcher("/QueryDept.jsp").forward(request, response);
			 
		} catch (SQLException e) {			
			e.printStackTrace();
			
			//�ֶ���ת��������ҳ			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}