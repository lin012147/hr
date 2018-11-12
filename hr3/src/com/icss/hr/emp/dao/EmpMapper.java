package com.icss.hr.emp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.hr.common.Pager;
import com.icss.hr.emp.pojo.Emp;

/**
 * Ա��dao
 * @author Administrator
 *
 */
public interface EmpMapper {

	void insert(Emp emp);
	
	void delete(Integer empId);
	
	List<Emp> queryByPage(Pager pager);
	
	/**
	 * �޸����ݣ��绰����ְ���ڣ����ʣ����ţ�ְ�����ҽ��ܣ�
	 */
	void update(Emp emp);
	
	Emp queryById(Integer empId);
	
	int getCount();
	
	/**
	 * ���ݵ�¼��emp_login_name��ѯ���ض�ӦԱ�����ݣ����룩
	 */
	Emp queryByName(String empLoginName);
	
	/**
	 * ͨ����¼���޸�����
	 */
	void updateEmpPwd(@Param("empLoginName") String empLoginName,@Param("empPwd") String empPwd);
	
	/**
	 * ͨ����¼������ͷ������
	 */
	void updateEmpPic(@Param("empLoginName") String empLoginName,@Param("empPic") String empPic);
	
	/**
	 * ͨ����¼����ѯͷ������ ����û������ڣ�����null
	 */
	String queryEmpPic(String empLoginName);
	
}