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
 * Ip查询
 */
@WebServlet("/IPServlet")
public class IPServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 响应输出流
		PrintWriter out = response.getWriter();

		// 获取请求参数
		String IP = request.getParameter("IP");

		// 创建默认的httpClient实例
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// 创建httppost
		String url = "https://api.avatardata.cn/IpLookUp/LookUp";
		HttpPost httppost = new HttpPost(url);

		// 创建参数队列
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", "8db08d98cd1a42d0a901705b70f30294"));
		params.add(new BasicNameValuePair("ip", IP));

		try {
			// 设置请求内容
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(params, "UTF-8");
			httppost.setEntity(uefEntity);

			// 发送post请求
			CloseableHttpResponse resp = httpclient.execute(httppost);

			// 响应实体
			HttpEntity entity = resp.getEntity();

			if (entity != null) {
				out.write(EntityUtils.toString(entity, "UTF-8"));
			}

			// 关闭，释放连接
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
