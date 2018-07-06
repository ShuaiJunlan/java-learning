package com.sh.thread.daemontest;

import org.junit.Test;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 7:45 PM 2018/07/05.
 */
public class TestDaemon {
    @Test
    public void test() {
        // Thread.currentThread().setDaemon(true); //java.lang.IllegalThreadStateException
        System.out.println(Thread.currentThread().isDaemon());
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("hello");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // default false
//        thread.setDaemon(false);
        thread.start();
    }
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().isDaemon());

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("hello");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // default false
        thread.setDaemon(false);
        thread.start();

    }
}
