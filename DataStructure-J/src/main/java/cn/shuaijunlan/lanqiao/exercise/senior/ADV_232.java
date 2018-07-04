package cn.shuaijunlan.lanqiao.exercise.senior;

import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 15:33 2017/3/16.
 */

/**
 * 矩阵乘法
 * 动态规划；
 * 数字超过了范围。long无法表示；
 * 最后一个测试超时；
 */
public class ADV_232 {
    public static int[] matrix;
    public static long[][] min_value;

    public static long getMinMultiValue(int n) {
        for (int i = n + 1; i >= 1; i--) {
            for (int j = i + 2; j <= (n + 1); j++) {
                long temp = Long.MAX_VALUE;
                for (int k = i + 1; k < j; k++)
                    temp = Math.min(temp, min_value[i][k] + min_value[k][j] + matrix[i] * matrix[k] * matrix[j]);
                min_value[i][j] = temp;
            }
        }
        return min_value[1][n + 1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        matrix = new int[n + 2];
        min_value = new long[n + 2][n + 2];
        for (int i = 1; i < (n + 2); i++) {
            matrix[i] = scanner.nextInt();
        }
        System.out.println(getMinMultiValue(n));
    }
}
