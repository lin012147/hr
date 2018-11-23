package com.icss.hr.analyze.dao;

import java.util.HashMap;
import java.util.List;

import com.icss.hr.analyze.dto.DeptEmpCount;

/**
 * ���ݷ���dao
 * @author Administrator
 *
 */
public interface AnaMapper {

	/**
	 * ��ѯ���ŵ�����,����DTO���ļ�������󣩴洢��ѯ���
	 * @throws Exception 
	 */
	List<DeptEmpCount> queryEmpCount();
	
	/**
	 * ְ��ƽ�����ʣ�����List��MapǶ�׽ṹ�洢��ѯ���
	 * @throws Exception 
	 */
	List<HashMap<String, Object>> queryAvgSal();
	
	/**
	 * ��ѯ���������߹���
	 * @return
	 * @throws Exception 
	 */
	List<HashMap<String, Object>> queryMinMaxSal();
	
	/**
	 * ��ѯ������Ϣ
	 * @throws SQLException 
	 */
	List<HashMap<String, Object>> queryDeptInfo();
	
}
