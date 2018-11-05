package Test;
/**
 * 测试员工类
 * @author Administrator
 *
 */
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import com.icss.hr.common.Pager;
import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.emp.dao.EmpDao;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.job.pojo.Job;

/**
 * 测试员工Dao
 * @author Administrator
 *
 */
public class TestEmpDao {
	
	private EmpDao dao = new EmpDao();
	
	@Test
	public void testInsert() throws SQLException {
		
		Dept dept = new Dept();
		dept.setDeptId(10);
		
		Job job = new Job();
		job.setJobId(1);
		
		Emp emp = new Emp("tom","tom","123456","tom@163.com",
				"10086",Date.valueOf("1995-01-01"),8000.0,dept,job,null,"无");
		
		dao.insert(emp);
	}
	
	@Test
	public void testInsert2() throws SQLException {
		
		Dept dept = new Dept();
		dept.setDeptId(10);
		
		Job job = new Job();
		job.setJobId(2);
		
		for (int i = 1;i <= 20;i ++) {
			Emp emp = new Emp("jack" + i,"jack" + i,"123456","jack" + i + "@163.com",
					"13812345678",Date.valueOf("1997-01-01"),7000.0,dept,job,null,"无");
			
			dao.insert(emp);
		}
				
	}
	@Test
	/**
	 * 错误
	 * @throws SQLException
	 */
	public void testUpdate() throws SQLException{
		Dept dept = new Dept();
		dept.setDeptId(60);
		
		Job job = new Job();
		job.setJobId(3);
		
		Emp emp = new Emp(2,"tom","tom","123456","tom@163.com",
				"10010",Date.valueOf("1951-12-31"),8000.0,dept,job,null,"精通数据库从删库到跑路");
		
		dao.update(emp);
	}
	
	@Test
	public void testQueryById() throws SQLException{
		Emp emp = dao.queryById(2);
		System.out.println(emp);
	}
	@Test
	public void testGetCount() throws SQLException{
		Integer i = dao.getCount();
		System.out.println(i);
	}
	
	@Test
	public void testQueryByName() throws SQLException{
		Emp emp = dao.queryByName("jack2");
		System.out.println(emp.getEmpPwd());
	}
	
	@Test
	public void testUpdateEmpPwd() throws SQLException{
		dao.updateEmpPwd("jack2", "456789");
	}
	@Test
	public void testUpdateEmpPic() throws SQLException{
		dao.updateEmpPic("jack2", "456789");
	}
	@Test
	public void testQueryEmpPic() throws SQLException{
		String pic = dao.queryEmpPic("jack2");
		System.out.println(pic);
	}
	
	@Test
	public void testQueryByPage() throws SQLException{
				
		Pager pager = new Pager(dao.getCount(),7,1);
		
		ArrayList<Emp> list = dao.queryByPage(pager);
		
		System.out.println(dao.getCount());
		
		for (Emp emp : list) {
			System.out.println(emp);
		}
		
	}
	
	
}
