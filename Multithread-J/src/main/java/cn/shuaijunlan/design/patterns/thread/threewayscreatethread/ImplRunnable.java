package cn.shuaijunlan.design.patterns.thread.threewayscreatethread;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 19:40 2017/4/10.
 */
public class ImplRunnable implements Runnable {
    public String name;

    public ImplRunnable(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println(name);
    }
}
