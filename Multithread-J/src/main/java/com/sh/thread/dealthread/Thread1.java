package com.sh.thread.dealthread;

/**
 * Created by Mr SJL on 2016/12/2.
 *
 * @Author Junlan Shuai
 */
public class Thread1 implements Runnable {
    Service service;

    public Thread1(Service service) {
        this.service = service;
    }

    public void run() {
        System.out.println("Thread " + Thread.currentThread().getName() + " is running");
        try {
            Thread.sleep(3000);
            this.service.printA();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
