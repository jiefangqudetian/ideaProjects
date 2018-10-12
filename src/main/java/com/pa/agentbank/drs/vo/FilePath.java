package com.pa.agentbank.drs.vo;

public class FilePath {
	
	private String filePath;
	//精确到毫秒，配置文件精确到毫秒
	private long timeVariable;
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public long getTimeVariable() {
		return timeVariable;
	}
	public void setTimeVariable(long timeVariable) {
		this.timeVariable = timeVariable * 1000;
	}
	
	

}
