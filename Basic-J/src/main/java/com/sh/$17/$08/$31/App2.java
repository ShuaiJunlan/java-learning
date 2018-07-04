package com.sh.$17.$08.$31;

import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 19:44 2017/8/31.
 */
public class App2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] num = new int[n];
        for (int i = 0; i < n; i++){
            num[i] = scanner.nextInt();
        }
        if (n % 2 == 0)
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}
