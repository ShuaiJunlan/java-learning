package cn.shuaijunlan.lanqiao.$2015.JAVA_A;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 20:59 2017/3/11.
 */
public class Nine {
    public static final int MOD = 1000000007;
    public static int init[] = {-1, 4, 5, 6, 1, 2, 3}; // 骰子对面
    public static boolean conflict[][] = new boolean[7][7]; // 冲突

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            conflict[a][b] = conflict[b][a] = true;
        }

        // dp[i][j] 代表，i个骰子且最顶面是j的情况种数 并且使用了滚动dp，否则会超空间
        BigInteger dp[][] = new BigInteger[2][7];
        int e = 0;
        for (int i = 1; i < 7; i++)
            dp[e][i] = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            e = 1 - e;
            for (int j = 1; j < 7; j++) {
                dp[e][j] = BigInteger.ZERO;
                for (int k = 1; k < 7; k++) {
                    if (!conflict[init[j]][k])
                        dp[e][j] = dp[e][j].add(dp[1 - e][k]).mod(new BigInteger(MOD + ""));
//                    System.out.println("dp[" + e + "][" + j + "]=" + dp[e][j]);
                }
            }
        }
//        System.out.println("e=" + e);
        BigInteger sum = BigInteger.ZERO;
        for (int i = 1; i < 7; i++) {
            sum = sum.add(dp[e][i]).mod(new BigInteger(MOD + ""));
        }
        System.out.println("sum = " + sum);
        System.out.println(sum.multiply(quickpow(4, n)).mod(
                new BigInteger(MOD + "")));
    }

    // 快速幂
    static BigInteger quickpow(int n, int m) {
        BigInteger n1 = new BigInteger(n + "");

        BigInteger t = BigInteger.ONE;
        while (m > 0) {
            if ((m & 1) == 1)
                t = t.multiply(n1).mod(new BigInteger(MOD + ""));
            n1 = n1.multiply(n1).mod(new BigInteger(MOD + ""));
            m >>= 1;
        }
        return t;
    }
}
