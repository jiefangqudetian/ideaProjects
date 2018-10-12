package com.kaishengit.quartz;

import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;

public class JobTest {

    @Test
    public void simpleTrigger() throws SchedulerException, IOException {

        //1.定义任务
        JobDetail jobDetail = JobBuilder.newJob(MyQuartzJob.class).build();
        //2.定义触发形式
        SimpleScheduleBuilder ssb = SimpleScheduleBuilder.simpleSchedule();
        ssb.withIntervalInSeconds(3);
        ssb.repeatForever();
        //3.通过触发形式创建触发器
        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(ssb).build();
        //4.创建任务调度者对象
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        //5.调度任务
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();

        System.in.read();

    }


    @Test
    public void cornTrigger() throws SchedulerException, IOException {
        //1.定义任务
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("phoneNumber","18888888888");

        JobDetail jobDetail = JobBuilder
                                .newJob(MyQuartzJob.class)
                                .setJobData(jobDataMap)
                                .build();
        //2.定义触发形式
        ScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/3 * * * * ?");
        //3.通过触发形式创建触发器
        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(scheduleBuilder).build();
        //4.创建任务调度者对象
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        //5.调度任务
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();

        System.in.read();


    }
}
