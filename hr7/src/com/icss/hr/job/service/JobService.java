package com.icss.hr.job.service;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.job.dao.JobMapper;
import com.icss.hr.job.pojo.Job;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 职务Service
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class JobService {

	@Autowired
	private JobMapper mapper;
	
	@Autowired
	private JedisPool jedisPool; //jedis连接池

	public void addJob(Job job) {
		mapper.insert(job);
		deleteRedids();
	}

	public void updateJob(Job job) {
		mapper.update(job);
		deleteRedids();
	}

	public void deleteJob(Integer jobId) {
		mapper.delete(jobId);
		deleteRedids();
	}

	@Transactional(readOnly = true)
	public Job queryJobById(Integer jobId) {
		return mapper.queryById(jobId);
	}

	@Transactional(readOnly = true)
	public List<Job> queryJob() {
		// jackson转换工具类
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			// 获得redis连接
			Jedis jedis = jedisPool.getResource();

			// 返回部门集合的json字符串
			String jsonStr = jedis.get("job_list");

			// 如果没有查到缓存，直接抛异常
			if (jsonStr == null || "".equals(jsonStr)) {
				throw new RuntimeException("未找到job_list数据");
			}

			// 如果有缓存，把json字符串转换为Dept对象集合
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Job.class);

			ArrayList<Job> list = objectMapper.readValue(jsonStr, javaType);

			jedis.close();

			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 如果缓存没有命中，查询物理数据库
		List<Job> list = mapper.query();

		// 数据库的查询结果同步到缓存中
		try {
			// 获得redis连接
			Jedis jedis = jedisPool.getResource();
			// 把List集合转换为json字符串
			String jsonStr = objectMapper.writeValueAsString(list);
			// 存储到redis键值对中
			jedis.set("job_list", jsonStr);
			jedis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 根据传入的输出流，把部门表数据转换为excel文件数据
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	@Transactional(readOnly = true)
	public void writeExcel(OutputStream out) throws IOException {

		// 返回部门的数据集合
		List<Job> list = mapper.query();

		// 工作簿
		HSSFWorkbook wb = new HSSFWorkbook();

		// 工作表
		HSSFSheet sheet = wb.createSheet("职务数据");

		// 标题行
		HSSFRow titleRow = sheet.createRow(0);

		titleRow.createCell(0).setCellValue("职务编号");
		titleRow.createCell(1).setCellValue("职务名称");
		titleRow.createCell(2).setCellValue("最低工资");
		titleRow.createCell(3).setCellValue("最高工资");

		// 数据行
		for (int i = 1; i <= list.size(); i++) {
			Job job = list.get(i - 1);
			HSSFRow row = sheet.createRow(i);
			row.createCell(0).setCellValue(job.getJobId());
			row.createCell(1).setCellValue(job.getJobName());
			row.createCell(2).setCellValue(job.getJobMinSal());
			row.createCell(3).setCellValue(job.getJobMaxSal());
		}

		// 写入数据流
		wb.write(out);
	}
	
	/**
	 * 删除缓存
	 */
	private void deleteRedids() {		
		//删除原始缓存数据
		try {
			//获得redis连接
			Jedis jedis = jedisPool.getResource();
			//删除缓存键值对
			jedis.del("job_list");			
			jedis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}
}