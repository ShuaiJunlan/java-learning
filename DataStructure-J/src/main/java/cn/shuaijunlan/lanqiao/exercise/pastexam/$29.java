package cn.shuaijunlan.lanqiao.exercise.pastexam;

import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 10:15 2017/3/21.
 */
public class $29 {
    public static long n;
    public static long m;
    public static long p;
    public static long n_v;
    public static long m_v;
    public static long re = 2;

    public static long fib(long aa, int k) {
        long a = 1;
        long b = 1;

        long c = 0;
        if (k == 1) {
            if (aa == 1) {
                c = 1;
                re = 1;
            }
            if (aa == 2) {
                c = 1;
                re = 2;
            } else {
                while (aa >= 3) {
                    c = (a + b) % m;
                    re = (re + c) % m;
                    a = b;
                    b = c;
                    aa--;
                }
            }
        } else {
            if (aa <= 2)
                c = 1;
            else {
                while (aa >= 3) {
                    c = (a + b);
                    a = b;
                    b = c;
                    aa--;
                }
            }
        }

        return c;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextLong();
        m = fib(scanner.nextLong(), 2);
        p = scanner.nextLong();
        fib(n, 1);
        System.out.println(re % p);
    }
}
