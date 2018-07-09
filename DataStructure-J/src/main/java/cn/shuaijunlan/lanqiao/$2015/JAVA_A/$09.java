package cn.shuaijunlan.lanqiao.$2015.JAVA_A;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 15:20 2017/3/11.
 */

import java.util.Scanner;

/**
 * 垒色子
 */
public class $09 {
    /**
     * 矩阵类
     */
    static class Matrix {
        public long[][] arr = new long[6][6];

        public Matrix(int arg) {
            if (arg == 1) {
                for (int i = 0; i < 6; i++) {
                    arr[i][i] = 1l;
                }
            }
        }
    }

    private static final long MOD = 1000000007L;    //  待求余的数

    /**
     * 矩阵的乘法
     *
     * @param a 矩阵a
     * @param b 矩阵b
     * @return 返回结果矩阵
     */
    public static Matrix mutliMatrix(Matrix a, Matrix b) {
        Matrix re = new Matrix(0);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 6; k++) {
                    re.arr[i][j] = re.arr[i][j] + (a.arr[i][k] * b.arr[k][j]) % MOD;
                }
            }
        }
        return re;
    }

    public static Matrix getResult(Matrix mutex, long n) {
        Matrix re = new Matrix(1);
        while (n != 0) {
            re = mutliMatrix(re, mutex);
            n--;
        }
        return re;
    }


    /**
     * 快速幂
     *
     * @param n
     * @return
     */
    public static long quickPowerOf4(int n) {
        long m = 1;
        long k = 4;
        while (n != 0) {
            if ((n & 1) == 1)
                m = (m * k) % MOD;
            n = n >> 1;
            k = (k * k) % MOD;
        }
        return m;
    }

    public static long[][] re = new long[2][7];     //  表示上表面为j时，第i个色子所有可能的情况
    public static boolean[][] conflict = new boolean[7][7]; //  表示冲突面
    public static int[] init = {0, 4, 5, 6, 1, 2, 3};       //  表示下标为i时，色子的对面为init[i]


    public static void main(String[] args) {
//        Matrix mutex = new Matrix(0);
//        for (int i = 0; i < 6; i++)
//        {
//            for (int j = 0; j < 6; j++)
//            {
//                mutex.arr[i][j] = 1;
//            }
//        }
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int m = scanner.nextInt();
//        for (int i = 0; i < m; i++)
//        {
//            int j = scanner.nextInt();
//            int k = scanner.nextInt();
//            mutex.arr[j-1][k-1] = 0;
//            mutex.arr[k-1][j-1] = 0;
//        }
//        Matrix re = getResult(mutex, n - 1);
//        long count = 0;
//        for (int i = 0; i < 6; i++)
//        {
//            for (int j = 0; j < 6; j++)
//            {
//                count = (count + re.arr[i][j])%MOD;
//            }
//        }
//        System.out.println(count * powerOf4(n) % MOD);
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            int j = scanner.nextInt();
            int k = scanner.nextInt();
            conflict[j][k] = true;
            conflict[k][j] = true;
        }

        int e = 0;
        for (int k = 1; k < 7; k++) {
            re[0][k] = 1;
        }
        for (int i = 2; i <= n; i++) {
            e = 1 - e;
            for (int j = 1; j < 7; j++) {
                re[e][j] = 0;
                for (int k = 1; k < 7; k++) {
                    if (!conflict[init[j]][k]) {
                        re[e][j] = (re[e][j] + re[1 - e][k]) % MOD;
                    }
                }
            }
        }
        long sum = 0;
        for (int i = 1; i < 7; i++) {
            sum = (re[e][i] + sum) % MOD;
        }
        System.out.println((sum * quickPowerOf4(n)) % MOD);

    }
}

