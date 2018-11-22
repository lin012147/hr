package com.icss.hr.common;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 邮件工具类
 * @author Administrator
 *
 */
public class MailUtil {
	
	/**
	 * @param from 发送方的邮件地址
	 * @param username 发送方的邮箱用户名
	 * @param userPwd 发送方的邮箱密码 
	 * @param to 接收方的邮箱地址
	 * @param title 邮件标题
	 * @param content 邮件内容
	 */
	public static void sendMail(String from,String username,String userPwd,String to,String title,String content) {
		
		// 配置参数
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");// 是否有身份验证
		props.setProperty("mail.transport.protocol", "smtp");// 使用的传输协议

		// 连接邮件服务器对象
		Session session = Session.getInstance(props);

		// 显示调试信息
		session.setDebug(true);

		// 信息对象
		Message msg = new MimeMessage(session);

		try {
			msg.setFrom(new InternetAddress(from));// 发送方的邮箱地址
			msg.setSubject(title);// 主题
			msg.setContent(content, "text/html;charset=utf-8");

			// 传输对象
			Transport transport = session.getTransport();

			// 连接smtp服务器和端口以及账号和密码
			transport.connect("192.168.30.254", 25, username, userPwd);

			// 接收方的邮箱地址
			transport.sendMessage(msg, new Address[] { new InternetAddress(to) });

			transport.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		System.out.println("邮件发送成功。。。。");
		
	}
	


}
