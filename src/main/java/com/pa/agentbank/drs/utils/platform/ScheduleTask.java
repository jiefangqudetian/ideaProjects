package com.pa.agentbank.drs.utils.platform;

import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.pa.agentbank.drs.vo.JobConfig;
import com.pa.agentbank.drs.vo.JobDetailConfig;

public class ScheduleTask {

	
	public static void buildScheduleTask(List<JobConfig> jobs) throws SchedulerException, ClassNotFoundException {
		
		/*根据Job创建定时任务*/
		for (JobConfig job : jobs) {
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			Scheduler scheduler =schedulerFactory.getScheduler();
			
			String jobClass = job.getJobClass();
			
			JobDetail jobDetail = JobBuilder
					.newJob(Class.forName(jobClass).asSubclass(Job.class))
					.withIdentity("myJob"+job.getName(), "group1").build();
			
			JobDetailConfig jobDetailConfig = job.getJobDetailConfig();
			jobDetail.getJobDataMap().put("jobDetailConfig", jobDetailConfig);
			
			CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder
					.cronSchedule(job.getCronExpression());
			CronTrigger cronTrigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger_1"+job.getName(), "tGroup1")
					.withSchedule(cronScheduleBuilder).build();
			scheduler.scheduleJob(jobDetail, cronTrigger);
			
			scheduler.start();
			
		}
	}
	
}
