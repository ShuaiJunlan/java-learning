package cn.shuaijunlan.lanqiao.exercise.algorithm;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 19:39 2017/3/17.
 */

/**
 * 求解两个数的最大公约数
 */
public class CommonDivisor {
    /**
     * 递归实现求两个数的最大公约数
     *
     * @param a
     * @param b
     * @return
     */
    public static int getMaxCommonDivisor(int a, int b) {
        if (a > b) {
            if (a % b == 0)
                return b;
            else
                return getMaxCommonDivisor(b, a % b);
        } else {
            if (b % a == 0)
                return a;
            else
                return getMaxCommonDivisor(a, b % a);
        }
    }

    public static void main(String[] args) {
        System.out.println(getMaxCommonDivisor(5, 10));
    }
}
