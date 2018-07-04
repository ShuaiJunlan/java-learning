package cn.shuaijunlan.nowcoder.$2017.$04.$11;

import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 19:57 2017/4/11.
 */
public class App1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if ((n & 1) == 0)
            System.out.println(3 * n / 6 * 2 * 2 * n % 6 / 2);
        else
            System.out.println(3 * (n - 3) / 6 * 2 + 1 * 2 * (n - 3) % 6 / 2);
    }

    public static int getMaxValue(int n) {
        return 0;
    }
}
