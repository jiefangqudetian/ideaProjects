package com.pa.agentbank.drs.vo;

public class JobConfig {

	private String name;
	private String group;
	private String jobDescription;
	private String cronExpression;
	private String jobClass;
	private String jobDetailConfigPath;
	private JobDetailConfig jobDetailConfig;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public String getJobClass() {
		return jobClass;
	}
	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}
	public String getJobDetailConfigPath() {
		return jobDetailConfigPath;
	}
	public void setJobDetailConfigPath(String jobDetailConfigPath) {
		this.jobDetailConfigPath = jobDetailConfigPath;
	}
	public JobDetailConfig getJobDetailConfig() {
		return jobDetailConfig;
	}
	public void setJobDetailConfig(JobDetailConfig jobDetailConfig) {
		this.jobDetailConfig = jobDetailConfig;
	}
	
	
}
