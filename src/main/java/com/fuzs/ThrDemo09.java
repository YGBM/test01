package com.fuzs;

public class ThrDemo09 implements Runnable{

    static int i = 0;
    
    public /*synchronized*/ void increase(){
        i++;
    }

    public static void main(String[] args) throws Exception {

        ThrDemo09 t = new ThrDemo09();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);

        t1.start();
        t2.start();
        t1.join();
        t2.join(); 
        System.out.println(i);   
    }

    @Override
    public void run() {
        for(int j = 0; j < 10000; j++){
            increase();
        }
    }
}
