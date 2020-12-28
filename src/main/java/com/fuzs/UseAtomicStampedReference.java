package com.fuzs;

import java.util.concurrent.atomic.AtomicStampedReference;

public class UseAtomicStampedReference {

    static AtomicStampedReference<String> asr = new AtomicStampedReference<>("zhangsan",0);
    public static void main(String[] args) throws InterruptedException {
        final int oldStamp = asr.getStamp();
        final String oldReference = asr.getReference();
        
        System.out.println((oldReference+"  -----------------------------  "+oldStamp));
        Thread rightStampThread = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+
            "current variable value:  "+oldReference+" current stamp: "+oldStamp+" - "+
            asr.compareAndSet(oldReference, oldReference+"java", oldStamp, oldStamp+1));
        },"rightStampThread");
        Thread errorStampThread = new Thread(()->{
            String reference = asr.getReference();
            System.out.println(
                Thread.currentThread().getName()+
                "current variable vaule: "+reference+" current stamp: "+asr.getStamp()+" - "+
                asr.compareAndSet(reference, reference+"C", oldStamp, oldStamp+1)
            );
        },"errorStampThread");

        rightStampThread.start();
        rightStampThread.join();

        errorStampThread.start();
        errorStampThread.join();
        System.out.println(asr.getReference()+" ----------------------- "+asr.getStamp());
    }
}
