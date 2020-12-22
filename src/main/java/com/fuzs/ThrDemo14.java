package com.fuzs;

public class ThrDemo14 implements Runnable {

    private static final Object o1 = new Object();
    private static final Object o2 = new Object();

    @Override
    public void run() {
        synchronized (o1) {
            System.out.println("进入第一个同步块");
            synchronized (o2) {
                System.out.println("进入第二个同步块");
                try {
                    o2.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args){
        ThrDemo14 run= new ThrDemo14();
        Thread t1 = new Thread(run);
        Thread t2 = new Thread(run);

        t1.start();
        t2.start();
    
    }
}
