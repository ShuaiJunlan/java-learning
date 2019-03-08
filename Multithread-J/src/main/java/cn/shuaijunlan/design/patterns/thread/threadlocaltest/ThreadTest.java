package cn.shuaijunlan.design.patterns.thread.threadlocaltest;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 17:38 2017/4/16.
 */
public class ThreadTest {
    private ThreadLocal threadLocal = new ThreadLocal();

    class ThreadA extends Thread {
        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 100; i++) {
                threadLocal.set(Thread.currentThread().getName() + " :" + i);
                System.out.println(threadLocal.get());
            }
        }
    }

    class ThreadB extends Thread {
        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 100; i++) {
                threadLocal.set(Thread.currentThread().getName() + " :" + i);
                System.out.println(threadLocal.get());
            }
        }
    }

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        ThreadA threadA = threadTest.new ThreadA();
        ThreadB threadB = threadTest.new ThreadB();
        threadA.start();
        threadB.start();
        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
