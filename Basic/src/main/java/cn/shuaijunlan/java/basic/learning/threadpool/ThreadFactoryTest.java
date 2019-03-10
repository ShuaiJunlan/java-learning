package cn.shuaijunlan.java.basic.learning.threadpool;

import java.util.PrimitiveIterator;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Junlan Shuaijunlan[shuaijunlan@gmail.com].
 * @since Created in 11:06 AM 10/13/18.
 */
public class ThreadFactoryTest implements ThreadFactory {
    private final AtomicInteger threadNumber = new AtomicInteger();
    private final String name;

    public ThreadFactoryTest(String name){
        this.name = name;

    }
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(name + threadNumber.incrementAndGet());
        return thread;
    }
}
