package com.kaishengit.spring.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SendWeixin implements Job {
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        String weixin = (String) jobDataMap.get("weixin");

        System.out.println("发送微信到："+weixin);
    }
}
