package com.sh.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 6:54 PM 9/26/19.
 */
public class Solution2 {
    private static volatile boolean flag = true;
    private static final Lock LOCK = new ReentrantLock();
    private static final Condition CONDITION1 = LOCK.newCondition(), CONDITION2 = LOCK.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            while (true) {
                LOCK.lock();
                try {
                    if (flag) {
                        sleep();
                        System.out.print("A");
                        CONDITION2.signal();
                        flag = false;
                    } else {
                        try {
                            CONDITION1.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } finally {
                    LOCK.unlock();
                }

            }
        });
        Thread thread2 = new Thread(() -> {
            while (true) {
                LOCK.lock();
                try {
                    if (!flag) {
                        sleep();
                        System.out.print("B");
                        CONDITION1.signal();
                        flag = true;
                    } else {
                        try {
                            CONDITION2.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } finally {
                    LOCK.unlock();
                }

            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    private static void sleep(){
        try {
            //for clearly seeing the printed character
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
