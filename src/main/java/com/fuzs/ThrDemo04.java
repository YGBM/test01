package com.fuzs;

import java.util.*;

public class ThrDemo04 {
    private static String[]            data       = { "data1", "data2", "data3", "data4", "data5", "data6", "data7",
		"data8", "data9", "data10", "data11", "data12", "data13", "data14", "data15", "data16", "data17", "data18",
		"data19", "data20"                   };
private static ArrayList<String>   keyList    = new ArrayList<String>();
private static Map<String, String> dataSource = new HashMap<String, String>();
private static int                 index      = 0;
 
/**
 * 初始化数据源
 */
static {
	for (int i = 0; i < data.length; i++) {
		String temp = Integer.toString(i);
		dataSource.put(temp, data[i]);
		keyList.add(temp);
	}
}
 
/**
 * 模拟从数据源中获取数据
 * 
 * @return
 */
public static String getSlowResponseData() {
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	return dataSource.get(Integer.toString((index++) % keyList.size()));
}
 
        public static void main(String[] args) {
            System.out.println(Runtime.getRuntime().availableProcessors());
        }
}
