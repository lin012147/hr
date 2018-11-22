package com.icss.hr.analyze.dao;

import java.util.HashMap;
import java.util.List;

import com.icss.hr.analyze.dto.DeptEmpCount;

/**
 * 数据分析dao
 * @author Administrator
 *
 */
public interface AnaMapper {

	/**
	 * 查询部门的人数,采用DTO（文件传输对象）存储查询结果
	 * @throws Exception 
	 */
	List<DeptEmpCount> queryEmpCount();
	
	/**
	 * 职务平均工资，采用List和Map嵌套结构存储查询结果
	 * @throws Exception 
	 */
	List<HashMap<String, Object>> queryAvgSal();
	
	/**
	 * 查询部门最低最高工资
	 * @return
	 * @throws Exception 
	 */
	List<HashMap<String, Object>> queryMinMaxSal();
	
	/**
	 * 查询部门信息
	 * @throws SQLException 
	 */
	List<HashMap<String, Object>> queryDeptInfo();
	
}
