package cn.shuaijunlan.nowcoder.$2017.$04.$07;

import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 20:11 2017/4/7.
 */
public class S2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long k = scanner.nextLong();
        long temp = n / k;
        long left = n % k;
        for (long i = temp; i >= 1; i--) {
            if (left < temp / 2) {
                temp = (i - 1);
                left = n - temp * k;
            } else {
                System.out.println(i);
                break;
            }
        }
    }
}
