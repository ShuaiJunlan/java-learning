package cn.shuaijunlan.java.basic.learning.lock.reentrantlock;

import com.sun.org.apache.bcel.internal.generic.LoadClass;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 3:43 PM 10/19/18.
 */
public class InterruptionTest {
    static Lock lock = new ReentrantLock();

    static class InnerClass implements Runnable{

        private Lock lock;
        public InnerClass(Lock lock){
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " is running!");
                lock.lockInterruptibly(); //java.lang.InterruptedException
                System.out.println(Thread.currentThread().getName() + " is over!");
            } catch (InterruptedException e) {
                System.out.println("Got an exception!");
                e.printStackTrace();
            }finally {
                lock.unlock();//java.lang.IllegalMonitorStateException
                System.out.println("Unlock! 1");
            }
        }
    }

    @Test
    public void test() {
        Lock lock = new ReentrantLock();
        InnerClass innerClass = new InnerClass(lock);
        Thread thread = new Thread(innerClass);
        try {
            lock.lock();
            thread.start();
            TimeUnit.SECONDS.sleep(2);
            thread.interrupt();
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
            System.out.println("Unlock! 2");
        }

    }
}
