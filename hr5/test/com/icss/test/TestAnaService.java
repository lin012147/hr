package com.icss.test;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.hr.analyze.dto.DeptEmpCount;
import com.icss.hr.analyze.service.AnaService;
import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.dept.service.DeptService;

/**
 * ���ݷ���service����
 * @author Administrator
 *
 */
public class TestAnaService {
	
	private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); 	
	
	private AnaService service = context.getBean(AnaService.class);
	
	@Test
	public void testQueryEmpCount() {
				
		List<DeptEmpCount> list = service.queryEmpCount();
		
		for (DeptEmpCount item : list) {
			System.out.println(item);
		}
		
	}	
	
	@Test
	public void testQueryAvgSal() {
				
		List<HashMap<String, Object>> list = service.queryAvgSal();
		
		for (HashMap<String, Object> item : list) {
			System.out.println(item);
		}
		
	}
	
	@Test
	public void testQueryMinMaxSal() {
						
		List<HashMap<String, Object>> list = service.queryDeptMinMaxSalary();
		
		for (HashMap<String, Object> item : list) {
			System.out.println(item);
		}
		
	}
	
	@Test
	public void testQueryDeptInfo() {
						
		List<HashMap<String, Object>> list = service.QueryDeptInfo();
		
		for (HashMap<String, Object> item : list) {
			System.out.println(item);
		}
		
	}	
}