package com.kaishengit.timer;

import java.util.TimerTask;

public class MyTimeTask extends TimerTask{
    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"Hello,TimeTask start");
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hello,TimeTask end");
    }
}
