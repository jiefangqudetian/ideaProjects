package com.pa.agentbank.drs.vo;

public class FileConfig {

	private int fileType;
	private String fileNameFormat;
	private String fileDetail;
	private long timeVariable;
	public int getFileType() {
		return fileType;
	}
	public void setFileType(int fileType) {
		this.fileType = fileType;
	}
	public String getFileNameFormat() {
		return fileNameFormat;
	}
	public void setFileNameFormat(String fileNameFormat) {
		this.fileNameFormat = fileNameFormat;
	}
	public String getFileDetail() {
		return fileDetail;
	}
	public void setFileDetail(String fileDetail) {
		this.fileDetail = fileDetail;
	}
	public long getTimeVariable() {
		return timeVariable;
	}
	public void setTimeVariable(long timeVariable) {
		this.timeVariable = timeVariable * 1000;
	}
	
	
}
