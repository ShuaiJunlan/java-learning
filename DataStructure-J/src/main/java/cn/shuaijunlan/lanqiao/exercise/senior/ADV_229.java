package cn.shuaijunlan.lanqiao.exercise.senior;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 10:32 2017/3/9.
 */

/**
 * 在一条直线上有n堆石子，每堆有一定的数量，每次可以将两堆相邻的石子合并，<br/>
 * 合并后放在两堆的中间位置，合并的费用为两堆石子的总数。求把所有石子合并成一堆的最小花费。
 */
public class ADV_229 {
    /**
     * 方法一：
     *
     * @param arrayList
     * @param sum
     */
    public static void mergeStone(ArrayList<Integer> arrayList, int sum) {
        if (arrayList.size() == 1) {
            System.out.println(sum + " " + arrayList.size());
        }
        for (int i = 0; i < arrayList.size() - 1; i++) {
            arrayList.set(i, arrayList.get(i) + arrayList.get(i + 1));
            int temp = arrayList.remove(i + 1);
            mergeStone(arrayList, sum + arrayList.get(i));
            arrayList.add(i + 1, temp);
        }
    }

    public static int[] sum_stone;
    public static int[][] min_stone;

    public static int getMinMergeValue(int n) {
        for (int i = n; i >= 1; i--) {
            for (int j = i + 1; j <= n; j++) {
                int temp = Integer.MAX_VALUE;
                for (int k = i; k < j; k++)
                    temp = Math.min(temp, min_stone[i][k] + min_stone[k + 1][j] + sum_stone[j] - sum_stone[i - 1]);
                min_stone[i][j] = temp;
            }
        }
        return min_stone[1][n];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int temp = num + 1;
        int[] stone = new int[temp];
        sum_stone = new int[temp];          //  存储前i个石子之和
        min_stone = new int[temp][temp];

//        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 1; i <= num; i++) {
            int t = scanner.nextInt();
            sum_stone[i] = t + sum_stone[i - 1];
//            arrayList.add(t);
            stone[i] = t;
        }
//        mergeStone(arrayList, 0);
        System.out.println(getMinMergeValue(num));
    }

}
