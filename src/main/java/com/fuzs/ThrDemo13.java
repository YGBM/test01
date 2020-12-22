package com.fuzs;

public class ThrDemo13 {

    public static final Object object = new Object();

    public static final CubbyHole cubbyHole = new CubbyHole();

    static class Thread001 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println("线程 " + Thread.currentThread().getName() + "在sync代码快中 开始执行");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程 " + Thread.currentThread().getName() + "在sync代码快中 继续执行");
            }
        }
    }

    static class Thread002 extends Thread{
        @Override
        public void run(){
            synchronized(object){
                object.notify();
                System.out.println("Thread "+Thread.currentThread().getName()+" called notify");
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"  正在执行 获得的结果为"+cubbyHole.get());;
        }).start();;
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"  正在执行 获得的结果为"+cubbyHole.get());;
        }).start();;

        Thread.sleep(1000);
        new Thread(()->{
            cubbyHole.put(9);
            System.out.println(Thread.currentThread().getName()+"  正在执行 设置值");;
        }).start();;
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"  正在执行 获得的结果为"+cubbyHole.get());;
        }).start();;
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"  正在执行 获得的结果为"+cubbyHole.get());;
        }).start();;
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"  正在执行 获得的结果为"+cubbyHole.get());;
        }).start();;
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"  正在执行 获得的结果为"+cubbyHole.get());;
        }).start();;

        Thread.sleep(1000);
        new Thread(()->{
            cubbyHole.put(9);
            System.out.println(Thread.currentThread().getName()+"  正在执行 设置值");;
        }).start();;
        Thread.sleep(1000);
        new Thread(()->{
            cubbyHole.put(9);
            System.out.println(Thread.currentThread().getName()+"  正在执行 设置值");;
        }).start();;
    }
}

class CubbyHole {
    private int contents;
    private volatile boolean available = false;

    public synchronized int get() {
        while (available == false) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        available = false;
        notifyAll();
        return contents;
    }

    public synchronized void put(int value) {
        while (available == true) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        contents = value;
        available = true;
        notifyAll();
    }
}