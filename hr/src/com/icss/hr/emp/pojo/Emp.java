package com.icss.hr.emp.pojo;


import java.sql.Date;

/**
 * 实体类
 * @author Administrator
 *
 */


import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.job.pojo.Job;
public class Emp {

	private Integer empId;
	
	private String empName;
	
	private String empLOgInName;
	
	private String empPwd;
	
	private  String empEmail;
	
	private String phone;
	
	private Date empHireDate;
	
	private Double empSalary;
	
	private Dept dept;//多对一关系
	
	private Job job;
	
	private String empPic;
	
	private String empInfo;

	public Emp() {
		super();
	}

	public Emp(String emp_name, String empLOgInName, String empPwd, String empEmail, String phone, Date empHireDate,
			Double empSalary, Dept dept, Job job, String empPic, String empInfo) {
		super();
		this.empName = emp_name;
		this.empLOgInName = empLOgInName;
		this.empPwd = empPwd;
		this.empEmail = empEmail;
		this.phone = phone;
		this.empHireDate = empHireDate;
		this.empSalary = empSalary;
		this.dept = dept;
		this.job = job;
		this.empPic = empPic;
		this.empInfo = empInfo;
	}

	public Emp(Integer empId, String emp_name, String empLOgInName, String empPwd, String empEmail, String phone,
			Date empHireDate, Double empSalary, Dept dept, Job job, String empPic, String empInfo) {
		super();
		this.empId = empId;
		this.empName = emp_name;
		this.empLOgInName = empLOgInName;
		this.empPwd = empPwd;
		this.empEmail = empEmail;
		this.phone = phone;
		this.empHireDate = empHireDate;
		this.empSalary = empSalary;
		this.dept = dept;
		this.job = job;
		this.empPic = empPic;
		this.empInfo = empInfo;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpLOgInName() {
		return empLOgInName;
	}

	public void setEmpLOgInName(String empLOgInName) {
		this.empLOgInName = empLOgInName;
	}

	public String getEmpPwd() {
		return empPwd;
	}

	public void setEmpPwd(String empPwd) {
		this.empPwd = empPwd;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getEmpHireDate() {
		return empHireDate;
	}

	public void setEmpHireDate(Date empHireDate) {
		this.empHireDate = empHireDate;
	}

	public Double getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(Double empSalary) {
		this.empSalary = empSalary;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getEmpPic() {
		return empPic;
	}

	public void setEmpPic(String empPic) {
		this.empPic = empPic;
	}

	public String getEmpInfo() {
		return empInfo;
	}

	public void setEmpInfo(String empInfo) {
		this.empInfo = empInfo;
	}

	@Override
	public String toString() {
		return "Emp [empId=" + empId + ", emp_name=" + empName + ", empLOgInName=" + empLOgInName + ", empPwd="
				+ empPwd + ", empEmail=" + empEmail + ", phone=" + phone + ", empHireDate=" + empHireDate
				+ ", empSalary=" + empSalary + ", dept=" + dept + ", job=" + job + ", empPic=" + empPic + ", empInfo="
				+ empInfo + "]";
	}

	
	
	
}
