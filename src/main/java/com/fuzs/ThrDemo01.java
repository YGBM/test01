package com.fuzs;

public class ThrDemo01 implements Runnable {

    @Override
    public void run() {
        System.out.println("this is a thead");

        System.out.println("this is a thead");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new ThrDemo01(),"thread1");
        t.start();
        // t.wait();
        System.out.println("this is main Thread");
    }
    
}
