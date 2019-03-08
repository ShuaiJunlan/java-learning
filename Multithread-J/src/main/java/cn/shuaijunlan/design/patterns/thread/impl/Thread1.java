package cn.shuaijunlan.design.patterns.thread.impl;

/**
 * Created by Mr SJL on 2016/11/19.
 *
 * @Author Junlan Shuai
 */
public class Thread1 extends Thread {
    public Thread1() {
        super();
    }

    public Thread1(String name) {
        super();
        super.setName(name);
    }

    private int count = 5;

    @Override
    synchronized public void run() {
//        while (count > 0)
//        {
//            count--;
//            System.out.println(this.currentThread().getName() +":"+ count);
//        }

        //  通过测试count--和--count，可以得出conut--是在println()之后执行的，--count是在println()之前执行的

        System.out.println(this.currentThread().getName() + ":" + (count--));
//        count--;

    }
}
