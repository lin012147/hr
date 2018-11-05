package Test;

import org.junit.Test;

import com.icss.hr.common.Pager;

public class TestPager {

	@Test
	public void test(){
		
		Pager pager = new Pager(25, 7,2);
		
		System.out.println("共" + pager.getRecordCount()+ 
				"条数据，每页" + pager.getPageSize() + "条数据，共"+
				pager.getPageCount() + "页，当前是第" + 
				pager.getStart() + "页到第" + 
				pager.getEnd() + "页");
	}
}
