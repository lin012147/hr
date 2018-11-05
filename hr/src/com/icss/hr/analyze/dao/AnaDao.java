package com.icss.hr.analyze.dao;

import java.nio.file.PathMatcher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.icss.hr.analyze.dto.DeptEmpCount;
import com.icss.hr.common.Dbutil;

import oracle.net.aso.l;

/**
 * 数据分析dao
 * @author Administrator
 *
 */
public class AnaDao {
	
	/**
	 * 查询部门的人数
	 * @throws SQLException 
	 */
	public ArrayList<DeptEmpCount> queryEmpCount() throws SQLException {
		
		Connection conn = Dbutil.getConnection();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT d.dept_name,");
		sql.append("       COUNT(e.emp_id) emp_count ");
		sql.append("FROM   emp e ");
		sql.append("RIGHT  OUTER JOIN dept d ON e.emp_dept_id = d.dept_id ");
		sql.append("GROUP  BY d.dept_name");
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<DeptEmpCount> list = new ArrayList<>();
		
		while (rs.next()) {
			DeptEmpCount dec = new DeptEmpCount(rs.getString(1),rs.getInt(2));
			list.add(dec);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return list;
	}
	/**
	 * 职务平均工资
	 * @throws SQLException 
	 * 
	 */
	public ArrayList<HashMap<String, Object>> queryAvgSal() throws SQLException {

		Connection conn = Dbutil.getConnection();

		StringBuffer sql = new StringBuffer();

		sql.append("SELECT j.job_name,");
		sql.append("       NVL(trunc(AVG(e.emp_salary),2),0) avg_sal ");
		sql.append("FROM   emp e ");
		sql.append("RIGHT  OUTER JOIN job j ON e.emp_job_id = j.job_id ");
		sql.append("GROUP  BY j.job_name");

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		
		ResultSet rs = pstmt.executeQuery();

		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		
		while (rs.next()) {			
			HashMap<String, Object> map = new HashMap<>();
			map.put("jobName", rs.getString(1));
			map.put("avgSal", rs.getInt(2));
			
			list.add(map);			
		}
		
		rs.close();
		pstmt.close();
		conn.close();

		return list;
	}
	/**
	 * 查询部门最低最高工资
	 * @throws SQLException 
	 */
	public ArrayList<HashMap<String, Object>> queryMinMax() throws SQLException{
		Connection conn = Dbutil.getConnection();
		StringBuffer sql = new StringBuffer();
		
		sql.append("select d.dept_name,min(e.emp_salary) 最小工资，max(e.emp_salary) 最大工资 " );
		sql.append("from emp e  ");
		sql.append("left outer join dept d on e.emp_dept_id = d.dept_id " );
		sql.append("group by d.dept_name");
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		
		while(rs.next()){
			HashMap<String, Object> map = new HashMap<>();
			map.put("deptName", rs.getString(1));
			map.put("minSal", rs.getInt(2));
			map.put("max", rs.getInt(3));
			
			list.add(map);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return list;
		
	}
	
}