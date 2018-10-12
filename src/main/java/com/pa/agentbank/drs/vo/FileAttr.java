package com.pa.agentbank.drs.vo;

import java.util.Date;

public class FileAttr {
	
	private String fileName;
	private Date   ModifyTime;
	private Long   size;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getModifyTime() {
		return ModifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		ModifyTime = modifyTime;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	
	

}
