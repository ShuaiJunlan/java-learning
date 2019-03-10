package cn.shuaijunlan.java.basic.learning.aqs;

import org.junit.Test;

import java.util.concurrent.locks.Lock;

import static org.junit.Assert.*;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 4:58 PM 11/18/18.
 */
public class TwinsLockTest {
    @Test
    public void test() throws InterruptedException {
        final Lock lock = new TwinsLock();
        class Worker extends Thread{
            @Override
            public void run() {
                while (true){
                    lock.lock();
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        lock.unlock();
                    }

                }
            }
        }
        for (int i = 0; i < 10; i++){
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }
        for (int i = 0; i < 100; i++){
            try {
                Thread.sleep(1000);
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread.sleep(100000);
    }

}