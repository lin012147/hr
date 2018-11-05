package com.icss.hr.job.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.dept.service.DeptService;
import com.icss.hr.job.service.JobService;

/**
 * Servlet implementation class WriteJobExcelServlet
 */
@WebServlet("/WriteJobExcelServlet")
public class WriteJobExcelServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�����ļ�ת��
		String filename = new String("ְλ����.xls".getBytes(), "iso8859-1");
		
		//����������Ը�����ʽ�������ݣ������ļ���
		response.setHeader("content-disposition", "attachment;filename=" + filename);
		
		//������Ӧ����Ϊ����������
		response.setContentType("application/octet-stream");
		
		
		//��Ӧ�����
		OutputStream out = response.getOutputStream();
		
		//����ҵ����
		JobService service = new JobService();
		
		try {
			service.writeExcel(out);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

}
