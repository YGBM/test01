package com.fuzs;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThrDemo05 {
    public static void main(String[] args){
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(()->{
            System.out.println("delay 3 seconds");
        }, 30, TimeUnit.SECONDS);
    }
}
