package com.sh.thread.test;

import com.sh.thread.impl.ALogin;
import com.sh.thread.impl.BLogin;

/**
 * Created by Mr SJL on 2016/11/20.
 *
 * @Author Junlan Shuai
 */
public class App2 {
    public static void main(String[] args) {
        Thread a = new ALogin();
        a.start();
        Thread b = new BLogin();

        b.start();
    }
}
