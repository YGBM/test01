package com.fuzs;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThrDemo06 {
    public static void main(String[] args){
        ScheduledExecutorService schedule = Executors.newScheduledThreadPool(5);
        schedule.scheduleAtFixedRate(()->{
            System.out.println("####################");
        }, 1, 2, TimeUnit.SECONDS) ;  
    }
}
