package com.sh.thread.dealthread;

/**
 * Created by Mr SJL on 2016/12/2.
 *
 * @Author Junlan Shuai
 */
public class Thread3 extends Thread {
    private Service service;

    public Thread3(Service service) {
        super();
        this.service = service;

    }

    @Override
    public void run() {
        super.run();

        try {
            System.out.println(Thread.currentThread().getName() + " is Running");
            Thread.sleep(3000);
            service.printA();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
