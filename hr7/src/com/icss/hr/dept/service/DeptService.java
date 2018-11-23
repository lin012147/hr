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
 * 部门Service
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class DeptService {
	
	@Autowired
	private DeptMapper mapper;	
	
	@Autowired
	private JedisPool jedisPool; //jedis连接池
	
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
	 * 返回部门列表,优先使用缓存
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Dept> queryDept()  {
		
		//jackson转换工具类
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			//获得redis连接
			Jedis jedis = jedisPool.getResource();
			
			//返回部门集合的json字符串
			String jsonStr = jedis.get("dept_list");
			
			//如果没有查到缓存，直接抛异常
			if (jsonStr == null || "".equals(jsonStr)) {
				throw new RuntimeException("未找到dept_list数据");
			}	
			
			//如果有缓存，把json字符串转换为Dept对象集合
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Dept.class);
			
			ArrayList<Dept> list = objectMapper.readValue(jsonStr, javaType);
						
			jedis.close();
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		//如果缓存没有命中，查询物理数据库
		List<Dept> list = mapper.query();
		
		//数据库的查询结果同步到缓存中
		try {
			//获得redis连接
			Jedis jedis = jedisPool.getResource();
			//把List集合转换为json字符串
			String jsonStr = objectMapper.writeValueAsString(list);
			//存储到redis键值对中
			jedis.set("dept_list", jsonStr);			
			jedis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return list;		
	}
	
	/**
	 * 根据传入的输出流，把部门表数据转换为excel文件数据
	 * @throws SQLException 
	 * @throws IOException 
	 */
	@Transactional(readOnly=true)
	public void writeExcel(OutputStream out) throws IOException {
		
		//返回部门数据集合
		List<Dept> list = mapper.query();
		
		//工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		
		//工作表
		HSSFSheet sheet = wb.createSheet("部门数据");
		
		//标题行
		HSSFRow titleRow = sheet.createRow(0);
		
		titleRow.createCell(0).setCellValue("部门编号");
		titleRow.createCell(1).setCellValue("部门名称");
		titleRow.createCell(2).setCellValue("部门地址");
		
		//数据行
		for (int i = 1;i <= list.size();i ++) {
			Dept dept = list.get(i - 1);
			HSSFRow row = sheet.createRow(i);
			row.createCell(0).setCellValue(dept.getDeptId());
			row.createCell(1).setCellValue(dept.getDeptName());
			row.createCell(2).setCellValue(dept.getDeptLoc());
		}
		
		//写入数据流
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
			jedis.del("dept_list");			
			jedis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}	
	
}