package Test;

import org.junit.Test;

import com.icss.hr.common.Pager;

public class TestPager {

	@Test
	public void test(){
		
		Pager pager = new Pager(25, 7,2);
		
		System.out.println("��" + pager.getRecordCount()+ 
				"�����ݣ�ÿҳ" + pager.getPageSize() + "�����ݣ���"+
				pager.getPageCount() + "ҳ����ǰ�ǵ�" + 
				pager.getStart() + "ҳ����" + 
				pager.getEnd() + "ҳ");
	}
}
