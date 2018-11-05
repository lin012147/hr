package com.icss.hr.pic.pojo;

import java.io.InputStream;
import java.util.Date;

public class Pic {

	private Integer picId;
	
	private String picName;
	
	private String picInfo;
	
	private Long picSize;
	
	private String picAuthor;
	
	private InputStream picData;
	
	private Date picDateTime;

	public Pic() {
		super();
	}

	public Pic(String picName, String picInfo, Long picSize, String picAuthor, InputStream picData, Date picDateTime) {
		super();
		this.picName = picName;
		this.picInfo = picInfo;
		this.picSize = picSize;
		this.picAuthor = picAuthor;
		this.picData = picData;
		this.picDateTime = picDateTime;
	}

	public Pic(Integer picId, String picName, String picInfo, Long picSize, String picAuthor, InputStream picData,
			Date picDateTime) {
		super();
		this.picId = picId;
		this.picName = picName;
		this.picInfo = picInfo;
		this.picSize = picSize;
		this.picAuthor = picAuthor;
		this.picData = picData;
		this.picDateTime = picDateTime;
	}

	public Integer getPicId() {
		return picId;
	}

	public void setPicId(Integer picId) {
		this.picId = picId;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getPicInfo() {
		return picInfo;
	}

	public void setPicInfo(String picInfo) {
		this.picInfo = picInfo;
	}

	public Long getPicSize() {
		return picSize;
	}

	public void setPicSize(Long picSize) {
		this.picSize = picSize;
	}

	public String getPicAuthor() {
		return picAuthor;
	}

	public void setPicAuthor(String picAuthor) {
		this.picAuthor = picAuthor;
	}

	public InputStream getPicData() {
		return picData;
	}

	public void setPicData(InputStream picData) {
		this.picData = picData;
	}

	public Date getPicDateTime() {
		return picDateTime;
	}

	public void setPicDateTime(Date picDateTime) {
		this.picDateTime = picDateTime;
	}

	@Override
	public String toString() {
		return "Pic [picId=" + picId + ", picName=" + picName + ", picInfo=" + picInfo + ", picSize=" + picSize
				+ ", picAuthor=" + picAuthor + ", picData=" + picData + ", picDateTime=" + picDateTime + "]";
	}
	
	
	
}
