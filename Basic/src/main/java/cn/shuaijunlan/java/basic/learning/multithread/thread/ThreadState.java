package cn.shuaijunlan.java.basic.learning.multithread.thread;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 11:11 AM 3/29/19.
 */
public class ThreadState {
    private final Object LOCK = new Object();
    private final Object LOCK1 = new Object();
    @Test
    public void test1() throws IOException, InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (LOCK){
                    try {
                        LOCK.wait(Integer.MAX_VALUE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (LOCK1){
                    try {
                        Thread.sleep(Integer.MAX_VALUE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                LockSupport.park();
            }
        });

        Thread.sleep(100);

        Thread thread5 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (LOCK1){
                    System.out.println("Thread 5");
                }
            }
        });

        thread1.setName("Thread 1");
        thread2.setName("Thread 2");
        thread3.setName("Thread 3");
        thread4.setName("Thread 4");
        thread5.setName("Thread 5");


        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        System.in.read();

    }
}
