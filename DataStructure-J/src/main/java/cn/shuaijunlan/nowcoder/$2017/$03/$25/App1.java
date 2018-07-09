package cn.shuaijunlan.nowcoder.$2017.$03.$25;

import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 14:33 2017/3/25.
 */
public class App1 {
    public static int n;
    public static int[] tx;
    public static int[] ty;

    public static int gx;
    public static int gy;

    public static int walkTime;
    public static int taxTime;

    public static int minTime = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        tx = new int[n];
        ty = new int[n];
        for (int i = 0; i < n; i++) {
            tx[i] = scanner.nextInt();
        }
        for (int j = 0; j < n; j++) {
            ty[j] = scanner.nextInt();
        }

        gx = scanner.nextInt();
        gy = scanner.nextInt();

        walkTime = scanner.nextInt();
        taxTime = scanner.nextInt();

        int temp = (Math.abs(gx) + Math.abs(gy)) * walkTime;
        minTime = minTime < temp ? minTime : temp;

        for (int i = 0; i < n; i++) {
            temp = (Math.abs(tx[i]) + Math.abs(ty[i])) * walkTime + (Math.abs(gx - tx[i]) + Math.abs(gy - ty[i])) * taxTime;
            minTime = minTime < temp ? minTime : temp;
        }
        System.out.println(minTime);
    }
}
