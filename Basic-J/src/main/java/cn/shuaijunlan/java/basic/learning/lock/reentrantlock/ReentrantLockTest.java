package cn.shuaijunlan.java.basic.learning.lock.reentrantlock;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Junlan Shuaijunlan[shuaijunlan@gmail.com].
 * @since Created in 8:19 PM 10/14/18.
 */
public class ReentrantLockTest {
    @Test
    public void test1(){
        ReentrantLock reentrantLock = new ReentrantLock();
        // reentrantLock.unlock(); // IllegalMonitorStateException
    }
}
