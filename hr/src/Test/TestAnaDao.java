package Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.icss.hr.analyze.dao.AnaDao;
import com.icss.hr.analyze.dto.DeptEmpCount;

public class TestAnaDao {
	AnaDao dao = new AnaDao();
	
	@Test
	public void TestQueryEmpCount() throws SQLException{
		ArrayList<DeptEmpCount> list = dao.queryEmpCount();
		
		for (DeptEmpCount dec : list){
			System.out.println(dec);
		}
	}
	
	@Test
	public void testQueryAvgSal() throws SQLException {
		
		ArrayList<HashMap<String, Object>> list = dao.queryAvgSal();
		
		for (HashMap<String, Object> map : list){
			System.out.println(map);
		}
		
	}
	@Test
	public void testQueryMinMax() throws SQLException{
		ArrayList<HashMap<String, Object>> list = dao.queryMinMax();
		
		for(HashMap<String, Object> mms : list){
			System.out.println(mms);
		}
	}


}
