package com.kaishengit.test;

import java.util.Date;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask {
    @Override
    public void run() {
        System.out.println("hello  "+new Date(System.currentTimeMillis()));
    }
}
