package com.pa.agentbank.drs.vo;

import java.util.List;

public class JobDetailConfig {

	private String jobName;
	private String hostIpAddr;
	private int hostPort;
	private String interval;
	private String startWeekParam;
	private FilePath hostDir;
	private String userName;
	private String userPassword;
	private FilePath localDir;
	private String dateDir;
	private String listDir;
	private FtpOpt ftpOpt;
	private List<FileConfig> fileList;
	private String jobForceEndTime;
	private String hasOk;
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getHostIpAddr() {
		return hostIpAddr;
	}
	public void setHostIpAddr(String hostIpAddr) {
		this.hostIpAddr = hostIpAddr;
	}
	public int getHostPort() {
		return hostPort;
	}
	public void setHostPort(int hostPort) {
		this.hostPort = hostPort;
	}
	public String getInterval() {
		return interval;
	}
	public void setInterval(String interval) {
		this.interval = interval;
	}
	public String getStartWeekParam() {
		return startWeekParam;
	}
	public void setStartWeekParam(String startWeekParam) {
		this.startWeekParam = startWeekParam;
	}
	public FilePath getHostDir() {
		return hostDir;
	}
	public void setHostDir(FilePath hostDir) {
		this.hostDir = hostDir;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public FilePath getLocalDir() {
		return localDir;
	}
	public void setLocalDir(FilePath localDir) {
		this.localDir = localDir;
	}
	public String getDateDir() {
		return dateDir;
	}
	public void setDateDir(String dateDir) {
		this.dateDir = dateDir;
	}
	public String getListDir() {
		return listDir;
	}
	public void setListDir(String listDir) {
		this.listDir = listDir;
	}
	public FtpOpt getFtpOpt() {
		return ftpOpt;
	}
	public void setFtpOpt(FtpOpt ftpOpt) {
		this.ftpOpt = ftpOpt;
	}
	public List<FileConfig> getFileList() {
		return fileList;
	}
	public void setFileList(List<FileConfig> fileList) {
		this.fileList = fileList;
	}
	public String getJobForceEndTime() {
		return jobForceEndTime;
	}
	public void setJobForceEndTime(String jobForceEndTime) {
		this.jobForceEndTime = jobForceEndTime;
	}
	public String getHasOk() {
		return hasOk;
	}
	public void setHasOk(String hasOk) {
		this.hasOk = hasOk;
	}
	
	
}
