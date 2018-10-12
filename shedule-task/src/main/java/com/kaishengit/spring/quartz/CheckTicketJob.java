package com.kaishengit.spring.quartz;

import org.quartz.*;
import org.springframework.context.ApplicationContext;

public class CheckTicketJob implements Job {
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {


        try {
            ApplicationContext applicationContext = (ApplicationContext) jobExecutionContext.getScheduler().getContext().get("applicationContext");
            System.out.println("ApplicationContext:" + applicationContext);
        } catch (SchedulerException e){
            e.printStackTrace();
        }

        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();

        String value = (String) jobDataMap.get("ticketState");
        System.out.println("检查年票......" + value);
    }
}
