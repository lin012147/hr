package Test;



import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.icss.hr.job.dao.JobDao;
import com.icss.hr.job.pojo.Job;

public class TestJobDao {

	private JobDao dao = new JobDao();
	
	@Test
	public void testInsert() throws SQLException{
		Job job = new Job("±£∞≤",6000,10000);
		dao.insert(job);
	}
	@Test
	public void testUpdate() throws SQLException{
		Job job = new Job(5,"÷˙¿Ì",10000,30000);
		dao.update(job);
	}
	
	@Test
	public void testDelete() throws SQLException{
		dao.delete(5);
	}
	@Test
	public void TestQueryById() throws SQLException{
		Job job = dao.queryById(111);
		System.out.println(job);
	}
	@Test
	public void TestQuery() throws SQLException{
		ArrayList<Job> list = dao.query();
		
		for (Job job : list ){
			System.out.println(job);
		}
	}
}
