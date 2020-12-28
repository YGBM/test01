package com.fuzs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
//自定义实现锁
public class SowhatLock implements Lock {

    private Sync sync = new Sync();

    @Override
    public void lock() { sync.acquire(1); }
    @Override
    public boolean tryLock() {  return false; }
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {  return sync.tryAcquireNanos(1,unit.toNanos(time)); }
    @Override
    public void unlock() {  sync.release(1); }
    @Override
    public Condition newCondition() {  return sync.newCondition(); }
    @Override
    public void lockInterruptibly() throws InterruptedException { }
   
    private class Sync extends AbstractQueuedSynchronizer {
     @Override
     protected boolean tryAcquire(int arg)  {
      assert arg == 1;
      if (compareAndSetState(0, 1)) {
       setExclusiveOwnerThread(Thread.currentThread());
       return true;
      }
      return false;
     }
   
     @Override
     protected boolean tryRelease(int arg)  {
      assert arg == 1;
      if (!isHeldExclusively()) {
       throw new IllegalMonitorStateException();
      }
      setExclusiveOwnerThread(null);
      setState(0);
      return true;
     }
   
     @Override
     protected boolean isHeldExclusively()  {
      return getExclusiveOwnerThread() == Thread.currentThread();
     }
   
     Condition newCondition() {
      return new ConditionObject();
     }
    }
    
}
