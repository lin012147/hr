package com.icss.hr.dept.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.dept.service.DeptService;

/**
 * ����excel����
 */
@WebServlet("/WriteDeptExcelServlet")
public class WriteDeptExcelServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//�����ļ���ת��
		String filename = new String("��������.xls".getBytes(),"iso-8859-1");
		
		//����������Ը�����ʽ�������ݣ������ļ���
		response.setHeader("content-disposition", "attachment;filename=" + filename);
		
		//������Ӧ����Ϊ����������
		response.setContentType("application/octet-stream");
		
		//��Ӧ�����
		OutputStream out = response.getOutputStream();
		
		//����ҵ����
		DeptService service = new DeptService();
		
		try {
			service.writeExcel(out);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
	}

}