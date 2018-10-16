package cn.shuaijunlan.java.basic.learning.lock.reentrantlock;

import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.NotThreadSafe;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 7:43 PM 2018/10/02.
 * The class will achieve an blocking queue basing on {@link ReentrantLock} and {@link Condition},
 *
 */
@Slf4j
@ThreadSafe
public class CustomBlockQueue<T> {

    private ReentrantLock lock = new ReentrantLock();

    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();

    private Integer maxLength = 1 << 4;

    private Integer putIndex = 0, takeIndex = 0;
    private Integer count = 0;

    private Object[] value;

    public CustomBlockQueue(){
        value = new Object[maxLength];
    }

    public CustomBlockQueue(Integer maxLength){
        this.maxLength = maxLength;
        value = new Object[maxLength];
    }

    public void put(T val) throws InterruptedException {
        lock.lock();
        try {
            while (count.equals(maxLength)){
                log.info("The queue is full!");
                full.await();
            }
            putIndex = putIndex % maxLength;
            value[putIndex++] = val;
            count++;
            empty.signal();
        }finally {
            lock.unlock();
        }
    }

    @SuppressWarnings("unchecked")
    public T take() throws InterruptedException {
        lock.lock();
        Object val;
        try {
            while (count == 0){
                empty.await();
            }
            takeIndex = takeIndex % maxLength;
            val = value[takeIndex++];
            count--;
            if (count < 0){
                log.info("count: {}", count);
                System.exit(1);
            }
            full.signal();
        }finally {
           lock.unlock();
        }
        return (T) val;
    }
}
