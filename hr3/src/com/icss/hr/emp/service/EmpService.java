package com.icss.hr.emp.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.hr.common.Pager;
import com.icss.hr.emp.dao.EmpMapper;
import com.icss.hr.emp.pojo.Emp;

/**
 * Ա��Service
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class EmpService {
	
	@Autowired
	private EmpMapper mapper;
	
	/**
	 * ��¼��֤
	 * ����1�û���������  2�������   3��¼�ɹ�
	 * @throws SQLException 
	 */
	@Transactional(readOnly=true)
	public int checkLogin(String empLoginName,String empPwd) {
		
		Emp emp = mapper.queryByName(empLoginName);
		
		if (emp == null) {
			return 1;
		} else if (!empPwd.equals(emp.getEmpPwd())) {
			return 2;
		}
		
		return 3;
	}
	
	/**
	 * ����û����Ƿ����
	 * ���ڷ���true�������ڷ���false
	 * @throws SQLException 
	 */
	@Transactional(readOnly=true)
	public boolean checkLoginName(String empLoginName) {
		
		Emp emp = mapper.queryByName(empLoginName);
		
		if (emp == null)
			return false;
		
		return true;		
	}
	
	/**
	 * ������Ա��
	 * @throws SQLException 
	 */
	public void addEmp(Emp emp) {
		mapper.insert(emp);
	}
	
	/**
	 * ͨ��id��ѯԱ������
	 * @throws SQLException 
	 */
	@Transactional(readOnly=true)
	public Emp queryEmpById(Integer empId) {
		
		return mapper.queryById(empId);
	}
	
	/**
	 * ����Ա���ܼ�¼��
	 * @throws SQLException 
	 */
	@Transactional(readOnly=true)
	public int getEmpCount() {
		
		return mapper.getCount();
	}
	
	/**
	 * ��ҳ��ѯԱ��
	 * @throws SQLException 
	 */
	@Transactional(readOnly=true)
	public List<Emp> queryEmpByPage(Pager pager) {
		
		return mapper.queryByPage(pager);
	}
	
	/**
	 * �޸�Ա��
	 * @throws SQLException 
	 */
	public void updateEmp(Emp emp) {
		mapper.update(emp);
	}
	
	/**
	 * ɾ��Ա��
	 * @throws SQLException 
	 */
	public void deleteEmp(Integer empId) {
		mapper.delete(empId);
	}
	
	/**
	 * ����ͷ��
	 * @throws SQLException 
	 */
	public void updateEmpPic(String empLoginName,String empPic) {
		mapper.updateEmpPic(empLoginName, empPic);		
	}
	
	/**
	 * ��ѯ����ͷ��
	 * @throws SQLException 
	 */
	@Transactional(readOnly=true)
	public String queryEmpPic(String empLoginName) {
		
		return mapper.queryEmpPic(empLoginName);
	}
	
	/**
	 * �޸�����
	 * @throws SQLException 
	 */
	public void updateEmpPwd(String empLoginName,String empPwd) {
		mapper.updateEmpPwd(empLoginName, empPwd);
	}
	
	/**
	 * ��ѯ���ص�ǰ����
	 * @throws SQLException 
	 */
	@Transactional(readOnly=true)
	public String queryEmpPwd(String empLoginName) {
		
		return mapper.queryByName(empLoginName).getEmpPwd();
	}
	
}