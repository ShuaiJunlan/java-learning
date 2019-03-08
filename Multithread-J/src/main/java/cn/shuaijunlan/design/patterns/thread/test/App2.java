package cn.shuaijunlan.design.patterns.thread.test;

import cn.shuaijunlan.design.patterns.thread.impl.ALogin;
import cn.shuaijunlan.design.patterns.thread.impl.BLogin;

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
