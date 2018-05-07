package com.kaishengit.spring.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class TaskJob {



    /*@Scheduled(fixedDelay = 1000)
    public void delayJob(){
        System.out.println("delayJob");
    }*/

    /*@Scheduled(fixedRate = 1000)
    public void rateJob(){
        System.out.println("rateJob");
    }*/

    /*@Scheduled(cron="0/5 * * * * ?")
    public void cornJob(){
        System.out.println("cronJob");
    }*/

    /*@Scheduled(fixedDelay = 1000)
    @Async
    public void rateJob() throws InterruptedException {
        int id = new Random().nextInt(1000);
        System.out.println(Thread.currentThread().getName()+"rateJob:begin"+id);
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName()+"rateJob:end");
    }*/

    //@Async("taskScheduler")


    @Scheduled(fixedDelay = 1000)
    public void asyncJob() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"Job:start");
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName()+"Job:end");
    }
}
