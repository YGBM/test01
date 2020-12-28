package com.benchmack;

import java.util.concurrent.TimeUnit;

public class UseSyn implements GoodsService{

    private GoodsInfo goodsInfo;
    
    public UseSyn(GoodsInfo goodsInfo){
        this.goodsInfo = goodsInfo;
    }

    @Override
    public synchronized GoodsInfo getNum() throws Exception {
        // TimeUnit.MILLISECONDS.sleep(5);
        return this.goodsInfo;
    }

    @Override
    public synchronized void setNum(int number) throws Exception {
        // TimeUnit.MILLISECONDS.sleep(5);
        goodsInfo.changeNumber(number);
    }
    
}
