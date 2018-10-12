package com.pa.agentbank.drs.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileOperationJob implements Job{
	
	private static final Logger logger = LoggerFactory.getLogger(FileOperationJob.class);

	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		ODSJob.excute(context);
		
	}
	
	

}
