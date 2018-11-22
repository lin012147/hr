package com.icss.test;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.FileCopyUtils;

import com.icss.hr.common.Pager;
import com.icss.hr.pic.pojo.Pic;
import com.icss.hr.pic.service.PicService;

/**
 * Õºø‚service≤‚ ‘
 * @author Administrator
 *
 */
public class TestPicService {
	
	private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); 	
	
	private PicService service = context.getBean(PicService.class);
	
	@Test
	public void testInsert() throws IOException {
				
		File file = new File("e:\\1.jpg");
		
		byte[] picData = FileCopyUtils.copyToByteArray(file);
		
		Pic pic = new Pic(file.getName(), "Œ‚“‡∑≤", file.length(), "tom", picData, new Date());
		
		service.addPic(pic);
		
	}
	
	@Test
	public void testDelete() throws IOException {
	
		service.deletePic(161);
		
	}	
	
	@Test
	public void testGetCount() throws IOException {
	
		int count = service.getPicCount();
		System.out.println(count);
		
	}
	
	@Test
	public void testQueryByPage() throws IOException {
	
		Pager pager = new Pager(service.getPicCount(),10,1);
		
		List<Pic> list = service.queryPicByPage(pager);
		
		for (Pic pic : list) {
			System.out.println(pic);
		}
		
	}
	
	@Test
	public void testQueryById() throws IOException {
	
		Pic pic = service.queryPicById(145);
		
		System.out.println(pic);
		
		File file = new File("e:\\333.jpg");
		
		FileCopyUtils.copy(pic.getPicData(), file);	
	}
		
}