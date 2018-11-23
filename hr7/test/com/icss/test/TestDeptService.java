package com.icss.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.dept.service.DeptService;

/**
 * ≤ø√≈service≤‚ ‘
 * @author Administrator
 *
 */
public class TestDeptService {
	
	private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); 	
	
	private DeptService service = context.getBean(DeptService.class);
	
	@Test
	public void testInsert() {
				
		Dept dept = new Dept("eee","eee");
		service.addDept(dept);
		
	}	
	
	@Test
	public void testQuery() {
		
		List<Dept> list = service.queryDept();
		
		for (Dept dept : list) {
			System.out.println(dept);
		}
		
	}	

}