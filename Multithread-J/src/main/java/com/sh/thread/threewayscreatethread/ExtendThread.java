package com.sh.thread.threewayscreatethread;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 19:41 2017/4/10.
 */
public class ExtendThread extends Thread {
    String name;

    public ExtendThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name);
    }
}
