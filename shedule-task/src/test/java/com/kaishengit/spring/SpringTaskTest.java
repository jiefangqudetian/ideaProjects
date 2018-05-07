package com.kaishengit.spring;


import com.kaishengit.spring.task.MyRunnableTask;
import com.kaishengit.spring.task.TaskJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-task-annotation.xml","classpath:spring-task.xml"})
public class SpringTaskTest {





    @Test
    public void start() throws IOException{
        System.out.println("Spring start");
        System.out.println(Thread.currentThread().getName());
        System.in.read();
    }
}
