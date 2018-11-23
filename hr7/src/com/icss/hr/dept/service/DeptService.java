package com.icss.hr.dept.service;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.dev.EFBiffViewer;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icss.hr.dept.dao.DeptMapper;
import com.icss.hr.dept.pojo.Dept;
import com.sun.awt.AWTUtilities.Translucency;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * ����Service
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class DeptService {
	
	@Autowired
	private DeptMapper mapper;	
	
	@Autowired
	private JedisPool jedisPool; //jedis���ӳ�
	
	public void addDept(Dept dept) {				
		mapper.insert(dept);	
		deleteRedids();
	}

	public void updateDept(Dept dept)  {
		mapper.update(dept);
		deleteRedids();
	}
	
	public void deleteDept(Integer deptId)  {
		mapper.delete(deptId);
		deleteRedids();
	}
	
	@Transactional(readOnly=true)
	public Dept queryDeptById(Integer deptId)  {
		return mapper.queryById(deptId);
	}
	
	/**
	 * ���ز����б�,����ʹ�û���
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Dept> queryDept()  {
		
		//jacksonת��������
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			//���redis����
			Jedis jedis = jedisPool.getResource();
			
			//���ز��ż��ϵ�json�ַ���
			String jsonStr = jedis.get("dept_list");
			
			//���û�в鵽���棬ֱ�����쳣
			if (jsonStr == null || "".equals(jsonStr)) {
				throw new RuntimeException("δ�ҵ�dept_list����");
			}	
			
			//����л��棬��json�ַ���ת��ΪDept���󼯺�
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Dept.class);
			
			ArrayList<Dept> list = objectMapper.readValue(jsonStr, javaType);
						
			jedis.close();
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		//�������û�����У���ѯ�������ݿ�
		List<Dept> list = mapper.query();
		
		//���ݿ�Ĳ�ѯ���ͬ����������
		try {
			//���redis����
			Jedis jedis = jedisPool.getResource();
			//��List����ת��Ϊjson�ַ���
			String jsonStr = objectMapper.writeValueAsString(list);
			//�洢��redis��ֵ����
			jedis.set("dept_list", jsonStr);			
			jedis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return list;		
	}
	
	/**
	 * ���ݴ������������Ѳ��ű�����ת��Ϊexcel�ļ�����
	 * @throws SQLException 
	 * @throws IOException 
	 */
	@Transactional(readOnly=true)
	public void writeExcel(OutputStream out) throws IOException {
		
		//���ز������ݼ���
		List<Dept> list = mapper.query();
		
		//������
		HSSFWorkbook wb = new HSSFWorkbook();
		
		//������
		HSSFSheet sheet = wb.createSheet("��������");
		
		//������
		HSSFRow titleRow = sheet.createRow(0);
		
		titleRow.createCell(0).setCellValue("���ű��");
		titleRow.createCell(1).setCellValue("��������");
		titleRow.createCell(2).setCellValue("���ŵ�ַ");
		
		//������
		for (int i = 1;i <= list.size();i ++) {
			Dept dept = list.get(i - 1);
			HSSFRow row = sheet.createRow(i);
			row.createCell(0).setCellValue(dept.getDeptId());
			row.createCell(1).setCellValue(dept.getDeptName());
			row.createCell(2).setCellValue(dept.getDeptLoc());
		}
		
		//д��������
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
			jedis.del("dept_list");			
			jedis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}	
	
}