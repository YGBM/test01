package com.fuzs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThrDemo03 {
    public static void main(String[] args) {
        ExecutorService ftp = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 100; i++) {
            final int index = i;
            ftp.execute(() -> {
                System.out.println(index);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
