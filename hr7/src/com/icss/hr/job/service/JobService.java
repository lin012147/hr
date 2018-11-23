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
 * ְ��Service
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
	private JedisPool jedisPool; //jedis���ӳ�

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
		// jacksonת��������
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			// ���redis����
			Jedis jedis = jedisPool.getResource();

			// ���ز��ż��ϵ�json�ַ���
			String jsonStr = jedis.get("job_list");

			// ���û�в鵽���棬ֱ�����쳣
			if (jsonStr == null || "".equals(jsonStr)) {
				throw new RuntimeException("δ�ҵ�job_list����");
			}

			// ����л��棬��json�ַ���ת��ΪDept���󼯺�
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Job.class);

			ArrayList<Job> list = objectMapper.readValue(jsonStr, javaType);

			jedis.close();

			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		// �������û�����У���ѯ�������ݿ�
		List<Job> list = mapper.query();

		// ���ݿ�Ĳ�ѯ���ͬ����������
		try {
			// ���redis����
			Jedis jedis = jedisPool.getResource();
			// ��List����ת��Ϊjson�ַ���
			String jsonStr = objectMapper.writeValueAsString(list);
			// �洢��redis��ֵ����
			jedis.set("job_list", jsonStr);
			jedis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * ���ݴ������������Ѳ��ű�����ת��Ϊexcel�ļ�����
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	@Transactional(readOnly = true)
	public void writeExcel(OutputStream out) throws IOException {

		// ���ز��ŵ����ݼ���
		List<Job> list = mapper.query();

		// ������
		HSSFWorkbook wb = new HSSFWorkbook();

		// ������
		HSSFSheet sheet = wb.createSheet("ְ������");

		// ������
		HSSFRow titleRow = sheet.createRow(0);

		titleRow.createCell(0).setCellValue("ְ����");
		titleRow.createCell(1).setCellValue("ְ������");
		titleRow.createCell(2).setCellValue("��͹���");
		titleRow.createCell(3).setCellValue("��߹���");

		// ������
		for (int i = 1; i <= list.size(); i++) {
			Job job = list.get(i - 1);
			HSSFRow row = sheet.createRow(i);
			row.createCell(0).setCellValue(job.getJobId());
			row.createCell(1).setCellValue(job.getJobName());
			row.createCell(2).setCellValue(job.getJobMinSal());
			row.createCell(3).setCellValue(job.getJobMaxSal());
		}

		// д��������
		wb.write(out);
	}
	
	/**
	 * ɾ������
	 */
	private void deleteRedids() {		
		//ɾ��ԭʼ��������
		try {
			//���redis����
			Jedis jedis = jedisPool.getResource();
			//ɾ�������ֵ��
			jedis.del("job_list");			
			jedis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}
}