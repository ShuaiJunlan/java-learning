package com.sh.thread.test;

import com.sh.thread.impl.StopThread;

/**
 * Created by Mr SJL on 2016/11/20.
 *
 * @Author Junlan Shuai
 */
public class App3 {
    public static void main(String[] args) {


        Thread a = new StopThread();
//        a.setDaemon(true);
        System.out.println(a.isAlive());
        a.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a.interrupt();
        System.out.println(a.isAlive());

//        a.setPriority(10);
        System.out.println("isAlive= " + a.isInterrupted());
        System.out.println("isAlive= " + a.interrupted());

        System.out.println("The end");
    }
}
