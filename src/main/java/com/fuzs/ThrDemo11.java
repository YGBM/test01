package com.fuzs;

public class ThrDemo11 {

    static  int i = 0;
    public static void main(String[] args) throws InterruptedException {
       Thread t1 =  new Thread(()->{
            for(int j = 0;j < 10000; j++){
                //synchronized(String.class)
                    i++;
                
            }
        });
        Thread t2 =  new Thread(()->{
            for(int j = 0;j < 10000; j++){
                //synchronized(String.class)
                    i++;
                
            }
        });

        //多线程运行
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        //单线程运行
        // t1.start();
        // t1.join();

        // t2.start();
        // t2.join();
        System.out.println(i);
    }
}
