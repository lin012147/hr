package Test;

import java.sql.SQLException;

import org.junit.Test;

import com.icss.hr.emp.service.EmpService;

/**
 * ≤‚ ‘‘±π§Service
 * @author Administrator
 *
 */
public class TestEmpService {

	private EmpService service = new EmpService();
	
	@Test
	public void testCheckLogin() throws SQLException{
		int result = service.checkLogin("XXX", "xxx");
		System.out.println(result);
	}
	
	@Test
	public void testCheckLogInName() throws SQLException{
		boolean flag = service.checkLoginName("tom");
		System.out.println(flag);
	}
	
}
