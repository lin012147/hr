package com.icss.hr.analyze.dto;
/**
 * 部门员工人数DTO
 * @author Administrator
 *
 */
public class DeptEmpCount {
	
	private String deptName;
	
	private Integer empCount;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getEmpCount() {
		return empCount;
	}

	public void setEmoCount(Integer emoCount) {
		empCount = emoCount;
	}

	public DeptEmpCount() {
		super();
	}

	public DeptEmpCount(String deptName, Integer emoCount) {
		super();
		this.deptName = deptName;
		empCount = emoCount;
	}

	@Override
	public String toString() {
		return "DeptEmpCount [deptName=" + deptName + ", EmpCount=" + empCount + "]";
	}
	
	
	
}
