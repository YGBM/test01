package com.fuzs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

public class SoWhatTest {
    public static int m = 0;
    public  static CountDownLatch latch  = new CountDownLatch(50);
    public static Lock lock = new SowhatLock();
   
    public static void main(String[] args) throws  Exception{
     Thread[] threads = new Thread[50];

     for (int i = 0; i < threads.length ; i++){
        threads[i] = new Thread(()->{
        try{
            lock.lock();
            for (int j = 0; j <100 ; j++){
            m++;
            }
        }finally{
            lock.unlock();
        }
        latch.countDown();
        });
     }

     for(Thread t : threads) t.start();
     latch.await();
     System.out.println(m);
    }
}
