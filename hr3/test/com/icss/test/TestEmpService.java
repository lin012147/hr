package com.icss.test;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.hr.common.Pager;
import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.emp.dao.EmpMapper;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.emp.service.EmpService;
import com.icss.hr.job.pojo.Job;

/**
 * Ա��service����
 * @author Administrator
 *
 */
public class TestEmpService {
	
	private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); 	
	
	private EmpService service = context.getBean(EmpService.class);
	
	@Test
	public void testInsert() {
		
		Dept dept = new Dept();
		dept.setDeptId(10);
		
		Job job = new Job();
		job.setJobId(1);
		
		Emp emp = new Emp("aaa11","aaa","123456","aaa@163.com",
				"10086",Date.valueOf("1995-01-01"),8000.0,dept,job,null,"��");
		
		service.addEmp(emp);		
	}	
	
	@Test
	public void testDelete() {
		
		service.deleteEmp(121);			
	}
	
	@Test
	public void testQueryByPage() {
		
		Pager pager = new Pager(service.getEmpCount(),7,2);
		
		List<Emp> list = service.queryEmpByPage(pager);
		
		for (Emp emp : list) {
			System.out.println(emp);
		}
				
	}
	
	@Test
	public void testGetCount() {		
		int count = service.getEmpCount();
		System.out.println(count);				
	}
	
	@Test
	public void testUpdate() {
		
		Dept dept = new Dept();
		dept.setDeptId(20);
		
		Job job = new Job();
		job.setJobId(2);
		
		Emp emp = new Emp();
		emp.setEmpId(6);
		emp.setEmpPhone("12306");
		emp.setEmpSalary(9700.0);
		emp.setEmpHiredate(Date.valueOf("2008-8-8"));
		emp.setEmpInfo("�ó��ܹ�");
		emp.setDept(dept);
		emp.setJob(job);
		
		service.updateEmp(emp);
		
	}
	
	@Test
	public void testQueryById() {
		
		Emp emp = service.queryEmpById(6);
		System.out.println(emp);
		
	}
	
	@Test
	public void testQueryByName() {
		
		EmpMapper mapper = context.getBean(EmpMapper.class);
		Emp emp = mapper.queryByName("tom");
		System.out.println(emp);
		
	}
	
	@Test
	public void testUpdateEmpPwd() {
		
		service.updateEmpPwd("tom", "666999");
		
	}
	
	@Test
	public void testUpdateEmpPic() {
		
		service.updateEmpPic("jack5", null);
		
	}
	
	@Test
	public void testQueryEmpPic() {
		
		String empPic = service.queryEmpPic("tom");
		System.out.println(empPic);
		
	}
	
	
}