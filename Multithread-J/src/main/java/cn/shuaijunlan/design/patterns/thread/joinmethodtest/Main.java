package cn.shuaijunlan.design.patterns.thread.joinmethodtest;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 20:21 2017/4/15.
 */
public class Main {
    public static void main(String[] args) {
        CountResult countResult = new CountResult();
        Thread threadA = new TestThread(countResult, 1, 10);
        Thread threadB = new TestThread(countResult, 1, 10);
        Thread threadC = new TestThread(countResult, 1, 10);
        threadA.start();
        threadB.start();
        threadC.start();
    }
}
