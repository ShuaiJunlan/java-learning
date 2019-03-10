package cn.shuaijunlan.java.basic.learning.multithread.deadlock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author Junlan Shuaijunlan[shuaijunlan@gmail.com].
 * @since Created in 10:48 AM 10/16/18.
 */
@Slf4j
public class DeadLockUnitTest {
    @Test
    public void test1() throws InterruptedException {
        log.info("Current thread name is {}, and the daemon of it is {}!", Thread.currentThread().getName(), Thread.currentThread().isDaemon());

        String lock1 = "Lock1";
        String lock2 = "Lock2";
        DeadLockTest deadLockTest1 = new DeadLockTest(lock1, lock2);
        DeadLockTest deadLockTest2 = new DeadLockTest(lock2, lock1);
        deadLockTest1.start();
        deadLockTest2.start();
        deadLockTest1.join();
        deadLockTest2.join();
    }
}
