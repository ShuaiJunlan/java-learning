package cn.shuaijunlan.design.patterns.thread.impl;

/**
 * Created by Mr SJL on 2016/11/20.
 *
 * @Author Junlan Shuai
 */
public class StopThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
//            if (i == 1)
//            {
//                break;
//            }
//            try
//            {
//                Thread.sleep(500);
//            } catch (InterruptedException e)
//            {
//                e.printStackTrace();
//            }
//            if (i == 2300)
//            {
//                break;
//            }
            System.out.println("i= " + i);
        }
        System.out.println("还在执行");
    }
}
