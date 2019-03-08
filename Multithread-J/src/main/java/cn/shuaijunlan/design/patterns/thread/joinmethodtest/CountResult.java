package cn.shuaijunlan.design.patterns.thread.joinmethodtest;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 20:17 2017/4/15.
 */
public class CountResult {
    public int getRe(int a, int b) {
        int temp = 1;
        for (int i = a; i <= b; i++) {
            temp *= i;
        }
        return temp;
    }
}
