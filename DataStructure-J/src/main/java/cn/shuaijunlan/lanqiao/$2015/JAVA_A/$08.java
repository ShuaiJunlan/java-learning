package cn.shuaijunlan.lanqiao.$2015.JAVA_A;

import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 9:36 2017/3/2.
 */

/**
 * 移动距离
 * <p>
 * X星球居民小区的楼房全是一样的，并且按矩阵样式排列。其楼房的编号为1,2,3...
 * 当排满一行时，从下一行相邻的楼往反方向排号。
 * 比如：当小区排号宽度为6时，开始情形如下：
 * <p>
 * 1  2  3  4  5  6
 * 12 11 10 9  8  7
 * 13 14 15 .....
 * <p>
 * 我们的问题是：已知了两个楼号m和n，需要求出它们之间的最短移动距离（不能斜线方向移动）
 * <p>
 * 输入为3个整数w m n，空格分开，都在1到10000范围内
 * w为排号宽度，m,n为待计算的楼号。
 * 要求输出一个整数，表示m n 两楼间最短移动距离
 */
public class $08 {
    public static int getDistance(int m, int n, int w) {
        int line1 = (m - 1) / w + 1;
        int column1 = m % w;
        if (column1 == 0)
            column1 = w;
        if (line1 % 2 == 0)
            column1 = w - column1 + 1;

        int line2 = (n - 1) / w + 1;
        int column2 = n % w;
        if (column2 == 0)
            column2 = w;
        if (line2 % 2 == 0)
            column2 = w - column2 + 1;

        return Math.abs(line1 - line2) + Math.abs(column1 - column2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int w = scanner.nextInt();
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        System.out.println(getDistance(m, n, w));
    }
}
