package com.kaishengit.timer;

import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;

public class MyTimeTaskTest {

    @Test
    public void test() throws IOException {
        MyTimeTask timeTask = new MyTimeTask();

        Timer timer = new Timer();
        //任务延迟固定的时间执行
        //timer.schedule(timeTask,0);
        timer.schedule(timeTask,new Date());

        //在指定的时间去执行任务
        //timer.schedule(timeTask,new Date());

        //延迟0毫秒，每秒钟执行一次
        //timer.schedule(timeTask,0,1000);
        //指定时间开始，每两秒钟执行一次
        //timer.schedule(timeTask,new Date(),2000);

        //timer.scheduleAtFixedRate(timeTask,0,1000);
        //timer.scheduleAtFixedRate(timeTask,new Date(),1000);

        System.in.read();

    }
}
