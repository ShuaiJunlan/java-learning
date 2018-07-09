package cn.shuaijunlan.nowcoder.$2017.$04.$18;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 18:45 2017/4/18.
 */

import java.util.HashSet;
import java.util.Scanner;

/**
 *
 */
public class App1 {
    static private HashSet<Integer> hashSet = new HashSet();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            int t = scanner.nextInt();
            hashSet.add(t);
        }
        int n = scanner.nextInt();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int t = scanner.nextInt();

            if (hashSet.contains(t)) {
                if (i == n - 1)
                    stringBuilder.append(t);
                else
                    stringBuilder.append(t + " ");
            }
        }
        System.out.println(stringBuilder.toString());
    }
}
