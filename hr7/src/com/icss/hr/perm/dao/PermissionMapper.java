package com.icss.hr.perm.dao;

import java.util.List;
import java.util.Map;

/**
 * Ȩ��dao 
 */
public interface PermissionMapper {

	/**
	 * ͨ����¼�����ض�Ӧ������Ȩ������ 	
	 */
	List<Map<String, Object>> queryPerm(String empLoginName);	
	
}