package com.icss.test;

import org.junit.Test;

import com.icss.hr.common.MailUtil;

/**
 * ����ͨ�ù���
 * @author Administrator
 *
 */
public class TestCommon {
	
	@Test
	public void testSendMail(){
		MailUtil.sendMail("admin@163.com", "admin@163.com", "123456", "jack@163.com", "aldjk", "lkjaads");
	}
}
