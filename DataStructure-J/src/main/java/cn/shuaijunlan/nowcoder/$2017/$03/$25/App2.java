package cn.shuaijunlan.nowcoder.$2017.$03.$25;

import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 14:52 2017/3/25.
 */
public class App2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] str = scanner.next().toCharArray();
        int g_num = 0;
        int b_num = 0;
        int g_b_num = 0;
        int b_g_num = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'G') {
                g_num++;
                g_b_num += b_num;
            } else if (str[i] == 'B') {
                b_num++;
                b_g_num += g_num;
            }
        }
        System.out.println(g_b_num < b_g_num ? g_b_num : b_g_num);
    }
}
