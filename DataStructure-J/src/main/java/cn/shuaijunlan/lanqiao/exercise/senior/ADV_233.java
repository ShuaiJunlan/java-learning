package cn.shuaijunlan.lanqiao.exercise.senior;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 16:43 2017/3/16.
 */
public class ADV_233 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        LinkedList<Integer> arrayList = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int t = scanner.nextInt();

            if (t == 1) {
                int m = scanner.nextInt();
                arrayList.offer(m);
            } else if (t == 2) {
                if (!arrayList.isEmpty())
                    System.out.println(arrayList.poll());
                else {
                    System.out.println("no");
                    return;
                }
            } else if (t == 3) {
                System.out.println(arrayList.size());
            }
        }

    }
}
