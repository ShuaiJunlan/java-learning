package cn.shuaijunlan.nowcoder.$2017.$04.$07;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 19:43 2017/4/7.
 */
public class S1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        char[] peo = scanner.next().toCharArray();
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < peo.length; i++) {
            if (peo[i] - '0' <= 9 && peo[i] - '0' >= 1) {
                int start = 0, end = peo.length - 1;
                if (i - (peo[i] - '0') >= 0)
                    start = i - (peo[i] - '0');
                if (i + (peo[i] - '0') <= end)
                    end = i + (peo[i] - '0');
                for (; start <= end; start++) {
                    if (peo[start] == 'X')
                        hashSet.add(start);
                }
            }
        }
        System.out.println(hashSet.size());
    }
}
