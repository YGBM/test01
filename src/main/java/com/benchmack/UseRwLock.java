package com.benchmack;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UseRwLock implements GoodsService {

    private GoodsInfo goodsInfo;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock getLock = lock.readLock();
    private final Lock setLock = lock.writeLock();

    public UseRwLock(GoodsInfo goodsInfo){
        this.goodsInfo  = goodsInfo;
    }

    @Override
    public GoodsInfo getNum() throws Exception {
        getLock.lock();
        try{
            // TimeUnit.MILLISECONDS.sleep(5);
            return this.goodsInfo;
        }finally{
            getLock.unlock();
        }
    }

    @Override
    public void setNum(int number) throws Exception {
        setLock.lock();
        try{
            // TimeUnit.MILLISECONDS.sleep(5);
            goodsInfo.changeNumber(number);
        }finally{
            setLock.unlock();
        }

    }
    
}
