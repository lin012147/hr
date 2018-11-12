package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialArray;

import org.apache.catalina.Server;
import org.junit.Test;

import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.dept.service.DeptService;

/**
 * ≤‚ ‘≤ø√≈service
 * @author Administrator
 *
 */
public class TestDeptService {
	
	DeptService service = new DeptService();
	
	@Test
	public void testWriteExcel() throws SQLException, IOException {
		
		FileOutputStream fos = new FileOutputStream("e:\\dept.xls");
		
		service.writeExcel(fos);
		
		fos.close();		
	}

}