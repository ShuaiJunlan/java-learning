package cn.shuaijunlan.design.patterns.thread.interruptedexc;


/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 17:06 2017/7/30.
 */
public class Test1 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            Thread.currentThread().interrupt();
            System.out.println("hello world");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread.yield();
        t1.run();
    }
}
