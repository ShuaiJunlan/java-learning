package cn.shuaijunlan.lanqiao.$2015.JAVA_A;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 9:25 2017/3/2.
 */

/**
 * 52张扑克牌，不算花色，从中抽取13张牌，不算先后顺序，一共有多少种抽法。
 */
public class $07 {
    public static int count = 0;

    public static void getValue(int stepNum, int totalNum) {
        if (stepNum == 13 || totalNum >= 13) {
            if (totalNum == 13)
                count++;
        } else {
            for (int i = 0; i < 5; i++) {
                getValue(stepNum + 1, totalNum + i);
            }
        }
    }

    public static void main(String[] args) {
        getValue(0, 0);
        System.out.println($07.count);
    }
}
