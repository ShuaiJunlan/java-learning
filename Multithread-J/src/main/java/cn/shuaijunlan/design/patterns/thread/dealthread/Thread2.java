package cn.shuaijunlan.design.patterns.thread.dealthread;

/**
 * Created by Mr SJL on 2016/12/2.
 *
 * @Author Junlan Shuai
 */
public class Thread2 implements Runnable {
    Service service;

    public Thread2(Service service) {
        this.service = service;
    }

    public void run() {
        System.out.println("Thread " + Thread.currentThread().getName() + " is running");
        try {
            Thread.sleep(3000);
            this.service.printB();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
