package cn.shuaijunlan.nowcoder.$2017.$03.$23;

import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 19:56 2017/3/23.
 */
public class App2 {
    public static int[][] str = new int[50][64];
    public static int count = 0;

    public static void get(char[] chars) {
        int[] temp = new int[64];
        for (int i = 0; i < chars.length; i++) {
            temp[chars[i] - 'A']++;
        }
        if (count == 0) {
            for (int i = 0; i < 64; i++) {
                str[count][i] = temp[i];
            }
            count++;
        }
        boolean tag = true;
        for (int i = 0; i < count; i++) {
            tag = true;
            for (int j = 0; j < 64; j++) {
                if (str[i][j] != temp[j]) {
                    tag = false;
                    break;
                }
            }
        }
        if (!tag) {
            for (int i = 0; i < 64; i++) {
                str[count][i] = temp[i];
            }
            count++;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            get(scanner.next().toCharArray());
        }
        System.out.println(count);
    }
}
