package cn.shuaijunlan.design.patterns.thread.impl;

/**
 * Created by Mr SJL on 2016/11/19.
 *
 * @Author Junlan Shuai
 */
public class ALogin extends Thread {
    @Override
    public void run() {
        LoginServlet.doPost("a", "aa");
    }
}
