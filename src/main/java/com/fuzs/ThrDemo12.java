package com.fuzs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThrDemo12 {
    public static void main(String[] args) {
        ExecutorService cacheThreadPool = Executors.newCachedThreadPool();
        cacheThreadPool.submit(new Task1());
        cacheThreadPool.shutdown();

    }
}

class Task1 implements Runnable {

    @Override
    public void run() {
        long start = System.nanoTime();
        System.out.println("the task1 is running: ");
        String str = new String("using");
        sleepy();

        System.out.println("cost time is : " + (System.nanoTime() - start) + " str: " + str.hashCode());
    }

    public void sleepy() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}