package cn.shuaijunlan.design.patterns.thread.impl;

/**
 * Created by Mr SJL on 2016/11/19.
 *
 * @Author Junlan Shuai
 */
public class Thread2 implements Runnable {
    private int count = 5;

    public void run() {


        System.out.println("Thread2 run");
    }
}
