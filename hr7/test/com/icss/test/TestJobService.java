package com.icss.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.job.pojo.Job;
import com.icss.hr.job.service.JobService;

/**
 * ÷∞ŒÒservice≤‚ ‘
 * @author Administrator
 *
 */
public class TestJobService {
	
	private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); 	
	
	private JobService service = context.getBean(JobService.class);
	
	@Test
	public void testInsert() {
		
		Job job = new Job("ccc",111,222);
		service.addJob(job);		
	}	
	
	@Test
	public void testQuery() {
		
		List<Job> list = service.queryJob();
		
		for (Job job : list) {
			System.out.println(job);
		}
		
	}	

}