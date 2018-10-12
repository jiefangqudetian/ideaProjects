package com.pa.agentbank.drs.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pa.agentbank.drs.config.LoadJobConfig;
import com.pa.agentbank.drs.utils.platform.ScheduleTask;
import com.pa.agentbank.drs.vo.JobConfig;



public class Start {

	private static final Logger logger = LoggerFactory.getLogger(Start.class);
	
	public static void main(String[] args) {
		logger.info("Try to pull up ODS dispatch platform");
		
		List<JobConfig> jobConfigList;
		
		try {
			jobConfigList = LoadJobConfig.loadConfig();
			ScheduleTask.buildScheduleTask(jobConfigList);
			
		} catch (Exception e) {
			logger.error("failed to pull up ODS dispatch platform");
			e.printStackTrace();
		}
		
		logger.info("success to pull up ODS dispatch platform");
	}
}
