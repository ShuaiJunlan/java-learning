package cn.shuaijunlan.java.basic.learning.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 7:43 PM 2018/10/02.
 * The class will achieve an blocking queue basing on {@link ReentrantLock} and {@link Condition},
 * and providing take() and put() methods.
 */
public class BlockQueueWithSignal<T> {

    private ReentrantLock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();

    private Integer maxLength = 1 << 4;

    private Integer putIndex = 0, takeIndex = 0;
    private Integer count = 0;

    private Object[] value;

    public BlockQueueWithSignal(){
        value = new Object[maxLength];
    }

    public BlockQueueWithSignal(Integer maxLength){
        this.maxLength = maxLength;
        value = new Object[maxLength];
    }

    public void put(T val) throws InterruptedException {
        lock.lock();
        try {
            if (count.equals(maxLength)){
                System.out.println("The queue is full!");
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
            full.signalAll();
        }finally {
           lock.unlock();
        }
        return (T) val;
    }

}
