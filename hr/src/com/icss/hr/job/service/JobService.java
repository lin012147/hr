package com.icss.hr.job.service;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.job.dao.JobDao;
import com.icss.hr.job.pojo.Job;

public class JobService {

	public JobDao dao = new JobDao();
	
	public void addJob(Job job) throws SQLException{
		dao.insert(job);
	}
	public void updateJob(Job job) throws SQLException{
		dao.update(job);
	}
	public void deleteJob(Integer jobId) throws SQLException{
		dao.delete(jobId);
		
	}
	public Job queryJobById(Integer jobId) throws SQLException{
		return dao.queryById(jobId);
	}
	public ArrayList<Job> queryJob() throws SQLException{
		return dao.query();
	}
	
	public void writeExcel(OutputStream out) throws SQLException, IOException{
		//返回职务数据集合
		ArrayList<Job> list = dao.query();
		
		//工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		
		//工作表
		HSSFSheet sheet = wb.createSheet("职位数据");
		
		//标题行
		HSSFRow titleRow = sheet.createRow(0);
		
		titleRow.createCell(0).setCellValue("职位编号");
		titleRow.createCell(1).setCellValue("职位名称");
		titleRow.createCell(2).setCellValue("职位最小工资");
		titleRow.createCell(3).setCellValue("职位最大工资");
		//数据行
		for(int i = 1;i <= list.size();i++){
			Job job = list.get(i-1);
			
			HSSFRow row  = sheet.createRow(i);
			row.createCell(0).setCellValue(job.getJobId());
			row.createCell(1).setCellValue(job.getJobName());
			row.createCell(2).setCellValue(job.getJobMinSal());
			row.createCell(3).setCellValue(job.getJobMaxSal());
		}
		wb.write(out);
		
	}
	
	
}
