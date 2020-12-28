package com.benchmack;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BusiApp {
    static final int readWriteRatio = 10;
    static final int minthreadCount = 3;

    private static class GetThread implements Runnable {

        private GoodsService goodsService;

        public GetThread(GoodsService goodsService) {
            this.goodsService = goodsService;
        }

        @Override
        public void run() {
            long start = System.nanoTime();
            for (int i = 0; i < 100; i++) {
                try {
                    goodsService.getNum();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "read staff data cost time "
                    + (System.nanoTime() - start) + "ns");
        }
    }

    private static class SetThread implements Runnable {
        private GoodsService goodsService;

        public SetThread(GoodsService goodsService) {
            this.goodsService = goodsService;
        }

        @Override
        public void run() {
            long start = System.nanoTime();
            Random r = new Random();
            for (int i = 0; i < 10; i++) {
                try {
                    // TimeUnit.MILLISECONDS.sleep(10);
                    goodsService.setNum(r.nextInt(10));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " write staff data cost: "+(System.nanoTime() - start)+"ns");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        GoodsInfo goodsInfo = new GoodsInfo("Cpu",100000,100000);
        // GoodsService goodsService = new UseSyn(goodsInfo);
        GoodsService goodsService = new UseRwLock(goodsInfo); //单次耗时770ms  用读写锁实现
        for(int i = 0;i < minthreadCount;i++){
            Thread setT = new Thread(new SetThread(goodsService));
            for(int j = 0; j < readWriteRatio;j++){
                Thread getT = new Thread(new GetThread(goodsService));
                getT.start();
            }
            // TimeUnit.MILLISECONDS.sleep(100);
            setT.start();
        }
    }
}
