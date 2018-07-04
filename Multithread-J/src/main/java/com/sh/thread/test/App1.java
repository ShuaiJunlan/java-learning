package com.sh.thread.test;

import com.sh.thread.impl.Thread1;
import com.sh.thread.impl.Thread2;

/**
 * Created by Mr SJL on 2016/11/19.
 *
 * @Author Junlan Shuai
 */
public class App1 {

    public static void main(String[] args) {
//        Thread thread = new Thread1("A");
//        Thread thread1 = new Thread1("B");
//        Thread thread2 = new Thread1("C");
//        Thread thread3 = new Thread1("D");
//        Thread thread4 = new Thread1("E");
//
//        thread.start();
//        thread1.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();

//
        Thread1 t = new Thread1();
        Thread a = new Thread(t, "A");
        Thread b = new Thread(t, "B");
        Thread c = new Thread(t, "C");
        Thread d = new Thread(t, "D");
        Thread e = new Thread(t, "E");

        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
    }
}
