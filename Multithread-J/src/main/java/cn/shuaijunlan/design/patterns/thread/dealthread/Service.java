package cn.shuaijunlan.design.patterns.thread.dealthread;

/**
 * Created by Mr SJL on 2016/12/2.
 *
 * @Author Junlan Shuai
 */
public class Service {
    synchronized public void printA() {
        System.out.println("name is A");
    }

    synchronized public void printB() {
        System.out.println("name is B");
    }
}
