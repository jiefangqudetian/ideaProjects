package com.kaishengit.spring;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-quartz.xml","classpath:spring-datasource.xml"})
public class QuartzTest {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Test
    public void run()throws IOException{
        System.out.println("Spring start...");

        System.in.read();
    }




}
