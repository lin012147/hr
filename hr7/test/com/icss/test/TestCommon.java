package com.icss.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.hr.common.MailUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 测试通用功能
 * @author Administrator
 *
 */
public class TestCommon {
	
	private ApplicationContext context = new ClassPathXmlApplicationContext("application-redis.xml");
	
	private JedisPool JedisPool =(JedisPool) context.getBean("jedisPool");
	
	@Test
	public void testSendMail(){
		MailUtil.sendMail("admin@163.com", "admin@163.com", "123456", "jack@163.com", "aldjk", "lkjaads");
	}
	@Test
	public void testInsertRedis(){
		
		Jedis jedis = JedisPool.getResource();
		
		jedis.set("deptName", "开发部");
		
		String deptName = jedis.get("deptName");
		System.out.println(deptName);
		
		jedis.close();
	}
}
