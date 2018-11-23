package com.icss.hr.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.hr.emp.dao.EmpMapper;
import com.icss.hr.emp.pojo.Emp;

/**
 * ���Ա����ְ�����գ�����ף���ʼ�
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly=true)
public class HireDateService {
	
	@Autowired
	private EmpMapper mapper;
	
	/**
	 * �����ְ������
	 */
	public void checkHiredate() {
		
		//���Ա���б�
		List<Emp> list = mapper.queryByHiredate();
		
		for (Emp emp : list) {
			//���͵����ʼ�
			String content = emp.getEmpName() + ",���ã�<br><br>&nbsp;&nbsp;"
					+ "�ǳ����ҵ�֪ͨ����������������ְ�����գ��뵽���²���ȡһ�ݹ�˾����Ʒ��"
					+ "<br><br>&nbsp;&nbsp;��˾���²�";
			
			MailUtil.sendMail("admin@163.com", "admin@163.com", "123456", emp.getEmpEmail(), "��ְ������֪ͨ", content);
		}
		
	}	

}