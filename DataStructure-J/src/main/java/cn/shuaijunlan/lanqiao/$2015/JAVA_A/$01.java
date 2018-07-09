package cn.shuaijunlan.lanqiao.$2015.JAVA_A;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 20:20 2017/3/1.
 */
public class $01 {
    public static int abandonNum(int num) {
        int sum = 0;
        while (num != 0) {
            if (num % 2 != 0) {
                sum++;
                num--;
            }
            num = num / 2;
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(abandonNum(1543));
        System.out.println(abandonNum(3));
        System.out.println(abandonNum(4));
        System.out.println(abandonNum(10));
    }

}
