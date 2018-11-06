package cn.shuaijunlan.java.basic.learning.lock.synchronization;

import org.junit.Test;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 9:16 AM 11/6/18.
 */
public class SynchronizationTest {
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            Object lock1 = lock;
            synchronized (lock1){
                try {
                    Thread.sleep(5000);
                    System.out.println("Hello lock1!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(1000);

        new Thread(() -> {
            Object lock2 = lock;
            synchronized (lock2){
                try {
                    Thread.sleep(2000);
                    System.out.println("Hello lock2!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        System.out.println("Hello main!");
    }
    @Test
    public void test1() throws InterruptedException {
        System.out.println(Thread.currentThread().isDaemon());
        new Thread(() -> {
            Object lock1 = lock;
            synchronized (lock1){
                try {
                    Thread.sleep(5000);
                    System.out.println("Hello lock1!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(1000);

        new Thread(() -> {
            Object lock2 = lock;
            synchronized (lock2){
                System.out.println("Hello lock2!");
            }
        }).start();
    }

}
