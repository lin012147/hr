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
 * �ʼ�������
 * @author Administrator
 *
 */
public class MailUtil {
	
	/**
	 * @param from ���ͷ����ʼ���ַ
	 * @param username ���ͷ��������û���
	 * @param userPwd ���ͷ����������� 
	 * @param to ���շ��������ַ
	 * @param title �ʼ�����
	 * @param content �ʼ�����
	 */
	public static void sendMail(String from,String username,String userPwd,String to,String title,String content) {
		
		// ���ò���
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");// �Ƿ��������֤
		props.setProperty("mail.transport.protocol", "smtp");// ʹ�õĴ���Э��

		// �����ʼ�����������
		Session session = Session.getInstance(props);

		// ��ʾ������Ϣ
		session.setDebug(true);

		// ��Ϣ����
		Message msg = new MimeMessage(session);

		try {
			msg.setFrom(new InternetAddress(from));// ���ͷ��������ַ
			msg.setSubject(title);// ����
			msg.setContent(content, "text/html;charset=utf-8");

			// �������
			Transport transport = session.getTransport();

			// ����smtp�������Ͷ˿��Լ��˺ź�����
			transport.connect("192.168.30.254", 25, username, userPwd);

			// ���շ��������ַ
			transport.sendMessage(msg, new Address[] { new InternetAddress(to) });

			transport.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		System.out.println("�ʼ����ͳɹ���������");
		
	}
	


}
