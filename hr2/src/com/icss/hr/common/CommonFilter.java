package com.icss.hr.common;

import java.io.IOException;

import javax.management.modelmbean.RequiredModelMBean;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 公共过滤器
 */
@WebFilter("/*")
public class CommonFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		
		//统一设置请求响应编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset-8");
		
		//设置允许其他域跨域请求
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		/******登录拦截******/
		
		//请求uri
		String uri = request.getRequestURI();
		System.out.println("请求uri：" + uri);
		
		//web应用明后
		String app = request.getContextPath();
		
		//判断是否是不需要登录验证的uri
		/*
		if (!uri.equals(app + "/") 
				&& !uri.equals(app + "/login.html")
				&& !uri.equals(app + "/LoginServlet")
				&& !uri.startsWith(app + "/css")
				&& !uri.startsWith(app + "/js")
				&& !uri.startsWith(app + "/images")) {
			
			//登录判断
			HttpSession session = request.getSession();			
			String empLoginName = (String) session.getAttribute("empLoginName");
			
			if (empLoginName == null) {
				response.sendRedirect(app + "/login.html");
				return;
			}
			
		}
		*/
				
		//继续向下执行
		chain.doFilter(arg0, arg1);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}