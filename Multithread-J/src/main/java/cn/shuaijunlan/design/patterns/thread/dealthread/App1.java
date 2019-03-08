package cn.shuaijunlan.design.patterns.thread.dealthread;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * Created by Mr SJL on 2016/12/2.
 *
 * @Author Junlan Shuai
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class App1 {
    @Test
    public void test1() {
        Service service = new Service();
//        Thread1 thread1 = new Thread1(service);
//        Thread2 thread2 = new Thread2(service);
        Thread thread1 = new Thread(new Thread1(service));
        Thread thread2 = new Thread(new Thread2(service));
        thread1.start();
        thread2.start();

    }

    @Test
    public void test2() {
        Service service = new Service();
        Thread1 thread1 = new Thread1(service);
        Thread thread = new Thread(thread1);
        thread.start();
    }

    @Test
    public void test3() {
        Service service = new Service();
        service.printA();
        Thread thread = new Thread3(service);
        thread.start();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Service service = new Service();
        Thread3 thread1 = new Thread3(service);
        Thread4 thread2 = new Thread4(service);
//        Thread thread1 = new Thread(new Thread1(service));
//        Thread thread2 = new Thread(new Thread2(service));
        thread1.start();
        thread2.start();
    }
}
