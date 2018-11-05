package com.icss.hr.common;

/**
 * ��ҳ������
 * @author Administrator
 *
 */
public class Pager {

	private int recordCount;//�ܼ�¼��
	
	private int pageSize;//ÿҳ������
	
	private int pageCount;//����ҳ
	
	private int pageNum;//��ǰҳ��
	
	private int start;//��ʼλ��
	
	private int end;//��ֹλ��
	
	public Pager (int recordCount,int pageSize ,int pageNum){
		
		//��������ֵ
		this.recordCount = recordCount;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		
		//���㹲��ҳ
		this.pageCount = this.recordCount / this.pageSize;
		
		if(this.recordCount % this.pageSize != 0)
			this.pageCount ++;
		
		//���㵱ǰҳ��
		if (this.pageNum < 1)
			this.pageNum = 1;
		if(this.pageNum > this.pageCount)
			this.pageNum= this.pageCount;
		
		//������ʼλ�ú���ֹλ��
		this.start = (this.pageNum - 1) * this.pageSize + 1;
		this.end = this.pageSize * this.pageNum;
		
		if(this.end > this.recordCount)
			this.end = this.recordCount;
	}
	public Pager (int recordCount ,int pageNum){
		this(recordCount ,10,pageNum);
	}
	
	
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
}
