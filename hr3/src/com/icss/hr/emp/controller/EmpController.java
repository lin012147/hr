package com.icss.hr.emp.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.hr.common.Pager;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.emp.service.EmpService;

/**
 * 员工控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/emp")
public class EmpController {
	
	@Autowired
	private EmpService service;
	
	@RequestMapping("/checkLoginName")
	@ResponseBody
	public String checkLoginName(String empLoginName,HttpServletRequest request,HttpServletResponse response){
		
		return String.valueOf(service.checkLoginName(empLoginName));
	}
	
	@RequestMapping("/add")	
	public void add(Emp emp,HttpServletRequest request,HttpServletResponse response){
		
		service.addEmp(emp);		
	}
	
	@RequestMapping("/query")	
	@ResponseBody
	public HashMap<String, Object> query(int pageNum,int pageSize,HttpServletRequest request,HttpServletResponse response){
		
		Pager pager = new Pager(service.getEmpCount(), pageSize,pageNum);
		
		List<Emp> list = service.queryEmpByPage(pager);
		
		HashMap<String, Object> map = new HashMap<>();		
		map.put("pager", pager);
		map.put("list", list);
				
		return map;
	}
	
	@RequestMapping("/delete")	
	public void delete(Integer empId,HttpServletRequest request,HttpServletResponse response){
		
		service.deleteEmp(empId);		
	}
	
	@RequestMapping("/get")	
	@ResponseBody
	public Emp get(Integer empId,HttpServletRequest request,HttpServletResponse response){
		
		return service.queryEmpById(empId);		
	}
	
	
	@RequestMapping("/update")	
	public void update(Emp emp,HttpServletRequest request,HttpServletResponse response){
		
		service.updateEmp(emp);	
	}
	@RequestMapping("/login")
	@ResponseBody
	public Integer login(String empLoginName,String empPwd,HttpServletRequest request,HttpServletResponse response){
		
		int result =service.checkLogin(empLoginName, empPwd);
		
		//判断登录成功，存储用户名到session
		if(result == 3){
			HttpSession session = request.getSession();
			
			session.setAttribute("empLoginName", empLoginName);
		}
		return result;
	}
	@RequestMapping("/getEmpPic")
	@ResponseBody
	public String getEmpPic(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		String empLoginName=(String)session.getAttribute("empLoginName");
		String empPic = service.queryEmpPic(empLoginName);
		return empPic;
	}
	@RequestMapping("/updateEmpPic")
	public void updateEmpPic(String empPic,HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		String empLoginName=(String)session.getAttribute("empLoginName");
		
		service.updateEmpPic(empLoginName, empPic);
	}
	@RequestMapping("/checkOldPwd")
	public String checkOldPwd(String oldPwd,HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		String empLoginName=(String)session.getAttribute("empLoginName");
		
		String empPwd = service.queryEmpPwd(empLoginName);
		if (oldPwd.equals(empPwd)) {
			return "yes";
		}else {
			return "no";
		}
	}
	@RequestMapping("/updateEmpPwd")
	public void updateEmpPwd(String empPwd,String oldPwd,HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		String empLoginName=(String)session.getAttribute("empLoginName");
		
		service.updateEmpPwd(empLoginName, empPwd);
	}
	
}
