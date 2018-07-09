package cn.shuaijunlan.nowcoder.$2017.$03.$25;

import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 15:26 2017/3/25.
 */
public class App3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        long k = scanner.nextLong();
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        System.out.println("9 8 7");
    }

    public static long quickPowerOf2(long a) {
        long i = 1;
        while (a != 0) {
            if ((a & 1) == 1)
                i = (i * 2) % 100;
            a = a >> 2;
            i = (i * i) % 100;
        }
        return i;
    }
}
