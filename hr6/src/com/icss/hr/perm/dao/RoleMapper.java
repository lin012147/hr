package com.icss.hr.perm.dao;

import java.util.List;
import java.util.Map;

/**
 * ��ɫdao
 * @author Administrator
 *
 */
public interface RoleMapper {
    
	/**
	 * ���ݵ�¼����ѯ���ض�Ӧ�Ľ�ɫ����
	 */
	List<Map<String, Object>> queryRole(String empLoginName);
		
}