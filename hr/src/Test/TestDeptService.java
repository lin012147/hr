package Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import com.icss.hr.dept.service.DeptService;

public class TestDeptService {

	DeptService Service = new DeptService();
	@Test
	public void testWriterExcel() throws SQLException, IOException{
		
		FileOutputStream fos = new FileOutputStream("e:\\dept.xls");
		
		Service.writeExcel(fos);
		
		fos.close();
	}
}
