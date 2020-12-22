package com.fuzs;

public class ThrDemo08 {

    private static int count = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    // 用 & 1位运算, 如果 与1的位运算为0 , 代表为偶数
                    if ((count & 1) == 0) {

                        System.out.println(Thread.currentThread().getName() + " : " + count++);
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"偶数").start();

        new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                  
                    // 用 & 1位运算, 如果 与1的位运算为1 , 代表为奇数
                    if ((count & 1) == 1) {
                        System.out.println(Thread.currentThread().getName() + " : " + count++);
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"奇数").start();
    }
}
