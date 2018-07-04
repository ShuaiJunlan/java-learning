package cn.shuaijunlan.lanqiao.exercise.pastexam;

import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 14:56 2017/3/21.
 */

/**
 * 针对每个人记录逆序对的数量，然后求和
 */
public class $31 {
    public static Point[] people;

    static class Point {
        int v;
        long count;
    }

    public static void sort(int left, int mid, int right) {
        int temp = right - left + 1;
        Point[] peo = new Point[temp];
        for (int i = 0; i < temp; i++) {
            peo[i] = new Point();
        }
        int i = 0;
        int t = left;
        int r = mid + 1;
        while (t <= mid && r <= right) {
            if (people[t].v <= people[r].v) {
                people[t].count = people[t].count + r - 1 - mid;
                peo[i++] = people[t++];
            } else {
                people[r].count = people[r].count + mid + 1 - t;
                peo[i++] = people[r++];
            }
        }
        if (r <= right)      //  下数组有剩余
        {
            System.arraycopy(people, r, peo, i, right - r + 1);
        }
        while (t <= mid)        //  上数组有剩余
        {
            people[t].count = people[t].count + right - mid;
            peo[i++] = people[t++];
        }
        System.arraycopy(peo, 0, people, left, right - left + 1);
    }

    public static void merge(int left, int right) {
        if (left < right) {
            int index = (left + right) / 2;
            merge(left, index);
            merge(index + 1, right);
            sort(left, index, right);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        people = new Point[n];
        for (int i = 0; i < n; i++) {
            people[i] = new Point();
            people[i].v = scanner.nextInt();
        }
        merge(0, people.length - 1);
        long count = 0;
        for (int i = 0; i < n; i++) {
            count += (people[i].count * (people[i].count + 1) / 2);
        }
        System.out.println(count);
    }
}
