package cn.shuaijunlan.lanqiao.$2015.JAVA_B;

import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 20:28 2017/3/7.
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //while (in.hasNextInt())
        //{
        //注意while处理多个case
        //String a = in.nextString();
        //BigInteger b = new BigInteger(a);
        long a = in.nextLong();
        for (long i = 1; i < a; i++) {
            if (isZ(i)) {
                for (long j = 2; j < a; j++) {
                    if (getM(i, j) > a) {
                        break;
                    } else if (getM(i, j) == a) {
                        System.out.print(i + " " + j);
                        return;
                    }
                }
            }

        }
        System.out.print("No");
        //}
    }

    public static boolean isZ(long b) {
        if (b == 1)
            return true;
        long t = 2;
        while (b % t != 0) {
            t++;
        }
        if (t == b)
            return true;
        return false;
    }

    public static long getM(long i, long j) {
        long t = 1;
        for (long k = 0; k < j; k++) {
            t *= i;
        }
        return t;
    }
}
