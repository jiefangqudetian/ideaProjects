package com.kaishengit.spring;

import com.kaishengit.spring.task.MyRunnableTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-task.xml")
public class ThreadPoolSchedureTest {

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @Test
    public void test() throws IOException {
        MyRunnableTask task = new MyRunnableTask();

        //taskScheduler.execute(task);
        //taskScheduler.schedule(task,new Date(System.currentTimeMillis()+5000));
        //延迟循环执行任务
        //taskScheduler.scheduleWithFixedDelay(task,2000);
        //在指定的时间开始延迟循环执行任务
        //taskScheduler.scheduleWithFixedDelay(task,new Date(),3000);
        //按照固定间隔执行任务
        //taskScheduler.scheduleAtFixedRate(task,3000);
        //在指定时间开始按照固定间隔执行任务
        //taskScheduler.scheduleAtFixedRate(task,new Date(),1);
        //基于Corn表达式执行任务
        //taskScheduler.schedule(task,new CronTrigger("0/10 * * * * ?"));

        //使用PeriodicTrigger执行任务

        //2秒后开始执行任务
        PeriodicTrigger periodicTrigger = new PeriodicTrigger(2, TimeUnit.SECONDS);
        //首次延迟5秒
        periodicTrigger.setInitialDelay(5);

        taskScheduler.schedule(task,periodicTrigger);



        System.in.read();
    }
}
