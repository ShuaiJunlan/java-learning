package cn.shuaijunlan.java.basic.learning.concurrent.thread;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 8:51 AM 3/26/19.
 */
public class ThreadABCTest {
    public static void main(String[] args) {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("a");
            }
        });
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    a.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("b");
            }
        });
        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    b.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("c");
            }
        });

        // while (true){
            a.start();
            // b.start();
            // c.start();
        // }
    }
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    @Test
    public void test1() throws InterruptedException {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (atomicInteger.get()%3 == 0){
                        System.out.println("A");
                        atomicInteger.incrementAndGet();
                    }
                }
            }
        });

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (atomicInteger.get()%3 == 1){
                        System.out.println("B");
                        atomicInteger.incrementAndGet();
                    }
                }
            }
        });

        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (atomicInteger.get()%3 == 2){
                        System.out.println("C");
                        atomicInteger.incrementAndGet();
                    }
                }
            }
        });
        a.start();
        b.start();
        c.start();
        Thread.sleep(100);
    }
}
