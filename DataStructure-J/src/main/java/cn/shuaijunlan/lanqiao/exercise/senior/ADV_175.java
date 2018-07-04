package cn.shuaijunlan.lanqiao.exercise.senior;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 16:33 2017/3/16.
 */
public class ADV_175 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] a = new int[3];
        a[0] = scanner.nextInt();
        a[1] = scanner.nextInt();
        a[2] = scanner.nextInt();
        Arrays.sort(a);
        System.out.println(a[2] + " " + a[1] + " " + a[0]);

    }
}
