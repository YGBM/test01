package com.fuzs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App {

    private static final int THREAD_COUNT = 30;

    private static ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.execute(() -> {
                try {
                    semaphore.acquire();
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("save date ...");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }
}
