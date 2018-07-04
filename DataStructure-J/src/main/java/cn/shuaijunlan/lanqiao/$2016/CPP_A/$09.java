package cn.shuaijunlan.lanqiao.$2016.CPP_A;

import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 9:22 2017/3/9.
 */
public class $09 {
    public static int min = Integer.MAX_VALUE;

    public static void getMinDis(char[] str, int left, int right, int count) {
        if (left == right || left - 1 == right || left - 2 == right) {
            min = count < min ? count : min;
        } else {
            if (left < right) {
                if (str[left] == str[right]) {
                    getMinDis(str, left + 1, right - 1, count);
                } else {
                    getMinDis(str, left + 1, right, count + 1);
                    getMinDis(str, left, right - 1, count + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        char[] a = str.toCharArray();
        getMinDis(a, 0, a.length - 1, 0);

        System.out.println(min);
    }
}
