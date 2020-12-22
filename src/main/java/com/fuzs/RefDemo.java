package com.fuzs;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class RefDemo {
    public static void main(String[] args) throws InterruptedException {
        Object value = new Object();
        ReferenceQueue referenceQueue = new ReferenceQueue();  
        Map<Object,Object> map = new HashMap<>();


        Thread thread = new Thread(() -> {
            try {
                int cnt = 0;
                WeakReference<byte[]> k;
                while((k = (WeakReference) referenceQueue.remove()) != null) {
                    System.out.println((cnt++) + "回收了:" + k);
                }
            } catch(InterruptedException e) {
                //结束循环
            }
        },"write");
        thread.setDaemon(true);
        thread.start();

        for(int i = 0; i < 10000; i++){
            byte[] bytes = new byte[1*1024*1024];
            WeakReference<byte[]> weakReference = new WeakReference<byte[]>(bytes,referenceQueue);
            map.put(weakReference, value);
            Thread.sleep(100);
        }

        System.out.println("map.size -> "+map.size());
    }
}
