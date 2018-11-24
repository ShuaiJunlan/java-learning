package cn.shuaijunlan.java.basic.learning.lock.locksupport;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 3:12 PM 11/24/18.
 */
public class LockSupportTest {
    @Test
    public void test1(){
        System.out.println("Hello waiting 1000ms");
        long start = System.currentTimeMillis();
        LockSupport.parkNanos(1000);
        long end = System.currentTimeMillis();
        System.out.printf("Finish, spending %s ms.", end - start);
    }
    @Test
    public void test2(){
        LockSupport.park();
        System.out.println("Blocking");
    }

    /**
     * unpark before park
     */
    @Test
    public void test3(){
        Thread thread = Thread.currentThread();
        LockSupport.unpark(thread);
        LockSupport.park();
        System.out.println("hello");
    }

    /**
     *Current thread will be blocked, because {@see LockSupport#park()} not supported reentrant lock.
     */
    @Test
    public void test4(){
        Thread thread = Thread.currentThread();
        LockSupport.unpark(thread);
        LockSupport.park();
        System.out.println("Parking");
        LockSupport.park();
        System.out.println("Blocking");
    }

    /**
     * Why not throws InterruptedException
     */
    @Test
    public void test5(){
        Thread thread = new Thread(new Runnable() {
            private int count = 0;
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                long end = 0;
                while ((end - start) <= 1000){
                    count++;
                    end = System.currentTimeMillis();
                }
                System.out.println("After 1 second. count =" + count);

                //Waiting for get permit
                LockSupport.park();
                System.out.println("Thread over." + Thread.currentThread().isInterrupted());
            }
        });
        thread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //interrupt thread
        thread.interrupt();
        System.out.println("main over");

    }


}
