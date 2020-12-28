package com.benchmack;

public class GoodsInfo {
    private final String name;
    private double totalMoney;
    private int storeNumber;

    public GoodsInfo(String name,double totalMoney,int storeNumber){
        this.name = name;
        this.totalMoney = totalMoney;
        this.storeNumber = storeNumber;
    }

    public void changeNumber(int sellNumber){
        this.totalMoney += sellNumber * 25;
        this.storeNumber -=sellNumber;
    }

}
