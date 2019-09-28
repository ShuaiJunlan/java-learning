package com.sh.test;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 5:26 PM 9/26/19.
 */
public class Solution {
    private static String[] str = new String[]{"12345", "678910"};
    private static volatile int i = 0;
    private static final Object LOCK = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            synchronized (LOCK){
                System.out.println(str[i]);
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (LOCK){
                System.out.println(str[i]);
                i++;
            }
        });

        thread1.start();
        Thread.sleep(1000);
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println("success");
    }
}
