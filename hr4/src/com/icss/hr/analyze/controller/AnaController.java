package com.icss.hr.analyze.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.hr.analyze.dto.DeptEmpCount;
import com.icss.hr.analyze.service.AnaService;

@Controller
@RequestMapping("/ana")
public class AnaController {
	@Autowired
	private AnaService service;
	
	@RequestMapping("queryEmpCount")
	@ResponseBody
	public List<DeptEmpCount> queryEmpCount(HttpServletRequest request,HttpServletResponse response){
		
		return service.queryEmpCount();
	}
	@RequestMapping("queryDeptMinMaxSalary")
	@ResponseBody
	public List<HashMap<String, Object>> queryDeptMinMaxSalary(HttpServletRequest request,HttpServletResponse response){
		
		return service.queryDeptMinMaxSalary();
	}
	@RequestMapping("queryAvgSal")
	@ResponseBody
	public List<HashMap<String, Object>> queryAvgSal(HttpServletRequest request,HttpServletResponse response){
		
		return service.queryAvgSal();
	}
	@RequestMapping("queryDeptInfo")
	@ResponseBody
	public List<HashMap<String, Object>> queryDeptInfo(HttpServletRequest request,HttpServletResponse response){
		
		return service.QueryDeptInfo();
	}
}
