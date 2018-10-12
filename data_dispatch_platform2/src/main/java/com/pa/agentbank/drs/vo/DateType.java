package com.pa.agentbank.drs.vo;

public enum DateType {

	DAY("day"),WEEK("week"),MONTH("month"),ALL("all");
	
	private String interval;
	
	private DateType(String interval) {
		this.interval = interval;
	}

	public String getInterval() {
		return interval;
	}

	public static DateType getDateType(String interval) {
		for(DateType dateType : DateType.values()) {
			if (dateType.interval.equals(interval)) {
				return dateType;
			}
		}
		return null;
	}
	
	
}
