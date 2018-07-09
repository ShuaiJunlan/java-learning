package cn.shuaijunlan.lanqiao.Test;

import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 11:34 2017/3/$23.
 */
public class App_m_1 {
    public static int[] bottle;
    public static int[] pos;
    public static int count = 0;
    public static int n;

    public static void exchangeBottle() {
        for (int i = 1; i <= n; i++) {
            if (bottle[i] != i) {
                int temp = bottle[i];
                swap(bottle, i, pos[i]);
                swap(pos, temp, i);
                count++;
            }

        }
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        bottle = new int[n + 1];
        pos = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            bottle[i] = scanner.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            pos[bottle[i]] = i;
        }
        exchangeBottle();
        System.out.println(count);
    }
}
