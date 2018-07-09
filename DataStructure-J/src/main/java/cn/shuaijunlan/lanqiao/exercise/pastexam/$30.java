package cn.shuaijunlan.lanqiao.exercise.pastexam;

import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 20:38 2017/3/20.
 */
public class $30 {
    public static long n;
    public static long s;
    public static long a;
    public static long b;
    public static long[][] f;
    public static final int MAXN = 1100;
    public static final long MOD = 100000007;
    public static int e = 0;

    public static void dp() {
        f = new long[2][MAXN * MAXN];
        f[e][0] = 1;
        for (int i = 1; i < n; i++) {
            e = 1 - e;
            int temp = i * (i + 1) / 2;
            for (int j = 0; j <= temp; j++) {
                if (i > j)
                    f[e][j] = f[1 - e][j];
                else
                    f[e][j] = (f[1 - e][j] + f[1 - e][j - i]) % MOD;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextLong();
        s = scanner.nextLong();
        a = scanner.nextLong();
        b = scanner.nextLong();
        dp();
        long temp = n * (n - 1) / 2;
        long re = 0L;
        for (int i = 0; i <= temp; i++) {
            long t = s - i * a + (temp - i) * b;
            if (t % n == 0) {
                re = (re + f[e][i]) % MOD;
            }
        }
        System.out.println(re);
    }
}
