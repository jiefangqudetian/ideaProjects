package com.pa.agentbank.drs.vo;

public enum FileType {

	SPECIFY_FILE_NAME(0),DATE_FILE_NAME(1),REGULAR_FILE_NAME(2);
	
	private final int type;
	
	private FileType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
	
	public static FileType geFileType(int type) {
		FileType[] values = FileType.values();
		for (FileType fileType : values) {
			if (fileType.getType() == type) {
				return fileType;
			}
		}
		
		return null;
	}
}
