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
 * 员工Service
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class EmpService {
	
	@Autowired
	private EmpMapper mapper;
	
	/**
	 * 登录验证
	 * 返回1用户名不存在  2密码错误   3登录成功
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
	 * 检查用户名是否存在
	 * 存在返回true，不存在返回false
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
	 * 增加新员工
	 * @throws SQLException 
	 */
	public void addEmp(Emp emp) {
		mapper.insert(emp);
	}
	
	/**
	 * 通过id查询员工数据
	 * @throws SQLException 
	 */
	@Transactional(readOnly=true)
	public Emp queryEmpById(Integer empId) {
		
		return mapper.queryById(empId);
	}
	
	/**
	 * 返回员工总记录数
	 * @throws SQLException 
	 */
	@Transactional(readOnly=true)
	public int getEmpCount() {
		
		return mapper.getCount();
	}
	
	/**
	 * 分页查询员工
	 * @throws SQLException 
	 */
	@Transactional(readOnly=true)
	public List<Emp> queryEmpByPage(Pager pager) {
		
		return mapper.queryByPage(pager);
	}
	
	/**
	 * 修改员工
	 * @throws SQLException 
	 */
	public void updateEmp(Emp emp) {
		mapper.update(emp);
	}
	
	/**
	 * 删除员工
	 * @throws SQLException 
	 */
	public void deleteEmp(Integer empId) {
		mapper.delete(empId);
	}
	
	/**
	 * 更新头像
	 * @throws SQLException 
	 */
	public void updateEmpPic(String empLoginName,String empPic) {
		mapper.updateEmpPic(empLoginName, empPic);		
	}
	
	/**
	 * 查询返回头像
	 * @throws SQLException 
	 */
	@Transactional(readOnly=true)
	public String queryEmpPic(String empLoginName) {
		
		return mapper.queryEmpPic(empLoginName);
	}
	
	/**
	 * 修改密码
	 * @throws SQLException 
	 */
	public void updateEmpPwd(String empLoginName,String empPwd) {
		mapper.updateEmpPwd(empLoginName, empPwd);
	}
	
	/**
	 * 查询返回当前密码
	 * @throws SQLException 
	 */
	@Transactional(readOnly=true)
	public String queryEmpPwd(String empLoginName) {
		
		return mapper.queryByName(empLoginName).getEmpPwd();
	}
	
}