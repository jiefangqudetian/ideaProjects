package com.pa.agentbank.drs.vo;

public enum FtpOpt {

	
	Download("download"),Upload("upload");
	private final String type;
	
	private FtpOpt(String type) {
		this.type = type;	
	}

	public String getType() {
		return type;
	}
	
	public static FtpOpt getByType(String type) {
		FtpOpt[] values = FtpOpt.values();
		for (FtpOpt ftpOpt : values) {
			if (ftpOpt.getType().equalsIgnoreCase(type)) {
				return ftpOpt;
			}
		}
		
		return null;
	}
	
	
}
