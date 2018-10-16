package cn.shuaijunlan.java.basic.learning.lock.reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 7:43 PM 2018/10/02.
 * The class will achieve an blocking queue basing on {@link ReentrantLock} and {@link Condition},
 *
 * 2018-10-16 16:04:07 INFO  cn.shuaijunlan.java.basic.learning.lock.reentrantlock.BlockQueueWithCondition     - count: -1202168
 * 2018-10-16 16:04:07 INFO  cn.shuaijunlan.java.basic.learning.lock.reentrantlock.BlockQueueTest     - 991
 * 2018-10-16 16:04:07 INFO  cn.shuaijunlan.java.basic.learning.lock.reentrantlock.BlockQueueWithCondition     - count: -1202169
 * and providing take() and put() methods.
 *
 *
 * Why not it is thread security?
 */
@Slf4j
public class BlockQueueWithCondition<T> {

    private ReentrantLock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();

    private Integer maxLength = 1 << 4;

    private Integer putIndex = 0, takeIndex = 0;
    private Integer count = 0;

    private Object[] value;

    public BlockQueueWithCondition(){
        value = new Object[maxLength];
    }

    public BlockQueueWithCondition(Integer maxLength){
        this.maxLength = maxLength;
        value = new Object[maxLength];
    }

    public void put(T val) throws InterruptedException {
        lock.lock();
        try {
            if (count.equals(maxLength)){
                log.info("The queue is full!");
                full.await();
            }
            putIndex = putIndex % maxLength;
            value[putIndex++] = val;
            count++;
            empty.signalAll();
        }finally {
            lock.unlock();
        }
    }

    @SuppressWarnings("unchecked")
    public T take() throws InterruptedException {
        lock.lock();
        Object val;
        try {
            if (count == 0){
                empty.await();
            }
            takeIndex = takeIndex % maxLength;
            val = value[takeIndex++];
            count--;
            if (count < 0){
                log.info("count: {}", count);
                System.exit(1);
            }
            full.signalAll();
        }finally {
           lock.unlock();
        }
        return (T) val;
    }
}
