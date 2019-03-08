package cn.shuaijunlan.design.patterns.thread.dealthread;

/**
 * Created by Mr SJL on 2016/12/2.
 *
 * @Author Junlan Shuai
 */
public class Thread4 extends Thread {
    private Service service;

    public Thread4(Service service) {
        super();
        this.service = service;

    }

    @Override
    public void run() {
        super.run();

        try {
            System.out.println(Thread.currentThread().getName() + " is Running");
            Thread.sleep(3000);
            service.printB();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
