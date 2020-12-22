package com.fuzs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThrDemo02 {

    public static void main(String[] args) {
        ExecutorService  e = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++)
        e.submit(new Task());

    }
}

class Task implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("the  current thread is: "+Thread.currentThread().getName());
    }
}