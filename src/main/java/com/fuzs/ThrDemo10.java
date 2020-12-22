package com.fuzs;

public class ThrDemo10 {
    static int i = 0;
    public /*synchronized*/ static void increase(){
        i++;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            for(int j = 0; j < 10000; j++){
                increase();
            }
        },"thread1");
        Thread t2 = new Thread(()->{
            for(int j = 0; j < 10000; j++){
                increase();
            }
        },"thread2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(i);
        
    }
}
