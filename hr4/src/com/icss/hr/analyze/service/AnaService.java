package com.icss.hr.analyze.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.hr.analyze.dao.AnaMapper;
import com.icss.hr.analyze.dto.DeptEmpCount;

/**
 * Êý¾Ý·ÖÎöservice
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly=true)
public class AnaService {

	@Autowired
	private AnaMapper mapper;

	public List<DeptEmpCount> queryEmpCount() {

		return mapper.queryEmpCount();
	}

	public List<HashMap<String, Object>> queryAvgSal() {

		return mapper.queryAvgSal();
	}

	public List<HashMap<String, Object>> queryDeptMinMaxSalary() {

		return mapper.queryMinMaxSal();
	}
	
	public List<HashMap<String, Object>> QueryDeptInfo() {
		return mapper.queryDeptInfo();
	}

}