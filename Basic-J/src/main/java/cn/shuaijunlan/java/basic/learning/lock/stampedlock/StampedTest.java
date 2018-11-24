package cn.shuaijunlan.java.basic.learning.lock.stampedlock;

import org.junit.Test;

import java.util.concurrent.locks.StampedLock;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 7:04 PM 11/24/18.
 */
public class StampedTest {
    @Test
    public void test1() throws InterruptedException {
        StampedLock stampedLock = new StampedLock();
        Thread thread = new Thread(() -> {
            System.out.println("tryOptimisticRead:" + System.currentTimeMillis());
            long stamped = stampedLock.tryOptimisticRead();
            // long stamped = stampedLock.tryReadLock();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (stampedLock.validate(stamped)){
                System.out.println("Read:" + System.currentTimeMillis());
            }else {
                System.out.println("Validate failure");
            }
        });
        Thread thread1 = new Thread(() -> {
            long stamp = stampedLock.tryWriteLock();
            System.out.println("Write lock:" + System.currentTimeMillis());
        });
        thread.start();
        thread1.start();


        thread.join();
        thread1.join();

    }
}
