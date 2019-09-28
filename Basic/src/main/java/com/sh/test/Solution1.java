package com.sh.test;


/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 5:40 PM 9/26/19.
 */
public class Solution1 {
    private static final Object LOCK = new Object();
    private static volatile boolean flag = false;
    private static volatile int atomicInteger = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            flag = true;
            while (true) {
                synchronized (LOCK) {
                    if (atomicInteger % 2 == 0) {
                        try {
                            //for clearly seeing the printed character
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.print("A");
                        atomicInteger++;
                        LOCK.notify();
                    } else {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        });
        Thread thread2 = new Thread(() -> {
            if (!flag) {
                synchronized (LOCK) {
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                while (true) {
                    synchronized (LOCK) {
                        if (atomicInteger % 2 == 1) {
                            try {
                                //for clearly seeing the printed character
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.print("B");
                            atomicInteger++;
                            LOCK.notify();
                        } else {
                            try {
                                LOCK.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println("success");
    }
}
