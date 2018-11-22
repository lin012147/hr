package com.icss.hr.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.hr.emp.dao.EmpMapper;
import com.icss.hr.emp.pojo.Emp;

/**
 * 检查员工入职纪念日，发送祝福邮件
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly=true)
public class HireDateService {
	
	@Autowired
	private EmpMapper mapper;
	
	/**
	 * 检查入职纪念日
	 */
	public void checkHiredate() {
		
		//获得员工列表
		List<Emp> list = mapper.queryByHiredate();
		
		for (Emp emp : list) {
			//发送电子邮件
			String content = emp.getEmpName() + ",您好！<br><br>&nbsp;&nbsp;"
					+ "非常荣幸的通知您，今天是您的入职纪念日，请到人事部领取一份公司的礼品！"
					+ "<br><br>&nbsp;&nbsp;公司人事部";
			
			MailUtil.sendMail("tom@163.com", "tom@163.com", "123456", emp.getEmpEmail(), "入职纪念日通知", content);
		}
		
	}	

}