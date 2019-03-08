package cn.shuaijunlan.design.patterns.thread.joinmethodtest;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 20:19 2017/4/15.
 */
public class TestThread extends Thread {
    public CountResult countResult = null;
    int a = 0;
    int b = 0;

    public TestThread(CountResult countResult, int a, int b) {
        this.countResult = countResult;
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        System.out.println(countResult.getRe(a, b));
    }

}
