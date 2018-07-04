package cn.shuaijunlan.nowcoder.$2017.$04.$27;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 19:38 2017/4/27.
 */
public class APP1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        HashSet<Integer> hashSet = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            hashSet.add(scanner.nextInt());
        }
        Object[] arr = hashSet.toArray();
        if (arr.length < 3) {
            System.out.println(-1);
            return;
        }
        Arrays.sort(arr);
        System.out.println(arr[2]);
    }
}
