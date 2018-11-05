package Test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.icss.hr.dept.dao.DeptDao;
import com.icss.hr.dept.pojo.Dept;

/**
 * 测试部门Dao
 * @author Administrator
 *
 */
public class TestDeptDao {

	private DeptDao dao = new DeptDao();
	
	@Test
	public void testInsert() throws SQLException{
		Dept dept = new Dept("开发部","北京海淀区");
		dao.insert(dept);
				
	}	@Test
	public void testUpdate() throws SQLException{
		Dept dept = new Dept(40,"财务部","上海黄浦区");
		dao.update(dept);
	}
	@Test
	public void testDelete() throws SQLException {
		
		dao.delete(20);
	}
	
	@Test
	public void testQueryById() throws SQLException{
		
		Dept dept = dao.queryById(10);
		System.out.println(dept);
	}
	
	@Test
	public void testQuery() throws SQLException{
		ArrayList<Dept> list = dao.query();
		
		for (Dept dept : list){
			System.out.println(dept);
		}
	}
	
}


