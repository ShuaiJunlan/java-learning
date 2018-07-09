package cn.shuaijunlan.lanqiao.exercise.primer;


import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 19:32 2017/3/3.
 */
public class $04 {
    private static final int MOD = 10007;

    public static long fi(long n) {
        if (n <= 0)
            return 0;
        if (n == 1 || n == 2)
            return 1;
        return fi(n - 1) % MOD + fi(n - 2) % MOD;
    }

    public static long fibonacci1(long n) {
        if (n <= 0)
            return 0;
        if (n <= 2)
            return 1;
        long a = 1;
        long b = 1;
        long re = 0;
        for (long i = 3; i <= n; i++) {
            re = a + b;
            if (re > 10007) {
                re -= 10007;
            }
            a = b;
            b = re;
        }
        return re;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        System.out.println(fibonacci1(n) == fi(n) % MOD);
        System.out.println(fibonacci1(n) + " " + fi(n));
    }
}
