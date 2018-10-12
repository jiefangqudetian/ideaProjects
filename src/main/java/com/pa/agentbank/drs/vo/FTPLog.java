package com.pa.agentbank.drs.vo;

import com.pa.agentbank.drs.utils.common.DateUtil;

public class FTPLog {
	private String host;
	private String operation;
	private int ReplyCode;
	private String localFile;
	private String remoteFile;
	private String ReplyCodeDesc;
	private String createTime = DateUtil.currentDateTime() ;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public int getReplyCode() {
		return ReplyCode;
	}
	public void setReplyCode(int replyCode) {
		ReplyCode = replyCode;
	}
	public String getLocalFile() {
		return localFile;
	}
	public void setLocalFile(String localFile) {
		this.localFile = localFile;
	}
	public String getRemoteFile() {
		return remoteFile;
	}
	public void setRemoteFile(String remoteFile) {
		this.remoteFile = remoteFile;
	}
	public String getReplyCodeDesc() {
		return ReplyCodeDesc;
	}
	public void setReplyCodeDesc(String replyCodeDesc) {
		ReplyCodeDesc = replyCodeDesc;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "FTPLog {" + 
				"host='" + host + '\'' + 
				", operation='" +  operation + '\'' + 
				", ReplyCode=" + ReplyCode + 
				", localFile='" + localFile + '\'' + 
				", remoteFile='" + remoteFile + '\'' + 
				", ReplyCodeDesc='" + ReplyCodeDesc + '\'' + 
				", createTime='" + createTime + '\'' + 
				"}";
	}
	
	
}
