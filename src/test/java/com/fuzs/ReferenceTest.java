package com.fuzs;


import junit.framework.TestCase;


import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by mbl on 17/10/2017.
 */
public class ReferenceTest extends TestCase {

  /**
   * StrongReference 是 Java 的默认引用实现,它会尽可能长时间的存活于 JVM 内,
   * 当没有任何对象指向它时 GC 执行后将会被回收
   */
  public void testStrongReference() {
    Object referent = new Object();

    /**
     * 通过赋值创建 StrongReference
     */
    Object strongReference = referent;

    assertSame(referent, strongReference);

    referent = null;
    System.gc();

    /**
     * StrongReference 在 GC 后不会被回收
     */
    assertNotNull(strongReference);
  }

  /**
   * 当所引用的对象在 JVM 内不再有强引用时, GC 后 weak reference 将会被自动回收
   */
  public void testWeakReference() {
    Object referent = new Object();
    WeakReference<Object> weakRerference = new WeakReference<Object>(referent);

    assertSame(referent, weakRerference.get());

    referent = null;
    System.gc();

    /**
     * 一旦没有指向 referent 的强引用, weak reference 在 GC 后会被自动回收
     */
    assertNull(weakRerference.get());
  }

  /**
   * WeakHashMap 使用 WeakReference 作为 key,
   * 一旦没有指向 key 的强引用, WeakHashMap 在 GC 后将自动删除相关的 entry
   */
  public void testWeakHashMap() throws InterruptedException {
    Map<Object, Object> weakHashMap = new WeakHashMap<Object, Object>();
    Object key = new Object();
    Object value = new Object();
    weakHashMap.put(key, value);

    assertTrue(weakHashMap.containsValue(value));

    key = null;
    System.gc();

    /**
     * 等待无效 entries 进入 ReferenceQueue 以便下一次调用 getTable 时被清理
     */
    Thread.sleep(1000);

    /**
     * 一旦没有指向 key 的强引用, WeakHashMap 在 GC 后将自动删除相关的 entry
     */
    assertFalse(weakHashMap.containsValue(value));
  }

  /**
   * SoftReference 于 WeakReference 的特性基本一致,
   * 最大的区别在于 SoftReference 会尽可能长的保留引用直到 JVM 内存不足时才会被回收(虚拟机保证),
   * 这一特性使得 SoftReference 非常适合缓存应用
   */
  public void testSoftReference() {
    Object referent = new Object();
    SoftReference<Object> softRerference = new SoftReference<Object>(referent);

    assertNotNull(softRerference.get());

    referent = null;
    System.gc();

    /**
     *  soft references 只有在 jvm OutOfMemory 之前才会被回收, 所以它非常适合缓存应用
     */
    assertNotNull(softRerference.get());
  }

  /**
   * 最关键的来了， Phantom Reference(幽灵引用) 与 WeakReference 和 SoftReference 有很大的不同,
   * 因为它的 get() 方法永远返回 null, 这也正是它名字的由来.
   * 一个永远返回 null 的 reference 要来何用, 请注意构造 PhantomReference 时的第二个参数 ReferenceQueue
   * (事实上 WeakReference & SoftReference 也可以有这个参数)，
   * PhantomReference 唯一的用处就是跟踪 referent  何时被 enqueue 到 ReferenceQueue 中.
   */
  public void testPhantomReferenceAlwaysNull() {
    Object referent = new Object();
    PhantomReference<Object> phantomReference = new PhantomReference<Object>(referent, new ReferenceQueue<Object>());

    /**
     * phantom reference 的 get 方法永远返回 null
     */
    assertNull(phantomReference.get());
  }

  /**
   * 当一个 WeakReference 开始返回 null 时， 它所指向的对象已经准备被回收， 这时可以做一些合适的清理工作.
   * 将一个 ReferenceQueue 传给一个 Reference 的构造函数，
   * 当对象被回收时，虚拟机会自动将这个对象插入到 ReferenceQueue 中，
   * WeakHashMap 就是利用 ReferenceQueue 来清除 key 已经没有强引用的 entries.
   * */
  public void testReferenceQueue() throws InterruptedException {
    Object referent = new Object();
    ReferenceQueue<Object> referenceQueue = new ReferenceQueue<Object>();
    WeakReference<Object> weakReference = new WeakReference<Object>(referent, referenceQueue);

    assertFalse(weakReference.isEnqueued());
    Reference<? extends Object> polled = referenceQueue.poll();
    assertNull(polled);

    referent = null;
    System.gc();

    assertTrue(weakReference.isEnqueued());
    Reference<? extends Object> removed = referenceQueue.remove();
    assertNotNull(removed);
  }

}