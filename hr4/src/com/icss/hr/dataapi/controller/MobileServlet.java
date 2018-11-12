package com.icss.hr.dataapi.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * �ֻ������ز�ѯ
 */
@WebServlet("/MobileServlet")
public class MobileServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ��Ӧ�����
		PrintWriter out = response.getWriter();

		// ��ȡ�������
		String mobileNumber = request.getParameter("mobileNumber");

		// ����Ĭ�ϵ�httpClientʵ��
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// ����httppost
		String url = "https://api.avatardata.cn/MobilePlace/LookUp";
		HttpPost httppost = new HttpPost(url);

		// ������������
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", "cb9d8415d427460494b2206e6c5f1aeb"));
		params.add(new BasicNameValuePair("mobileNumber", mobileNumber));

		try {
			// ������������
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(params, "UTF-8");
			httppost.setEntity(uefEntity);

			// ����post����
			CloseableHttpResponse resp = httpclient.execute(httppost);

			// ��Ӧʵ��
			HttpEntity entity = resp.getEntity();

			if (entity != null) {
				out.write(EntityUtils.toString(entity, "UTF-8"));
			}

			// �رգ��ͷ�����
			resp.close();
			httpclient.close();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
