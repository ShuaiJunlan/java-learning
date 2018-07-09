package cn.shuaijunlan.lanqiao.simu5;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 15:46 2017/4/4.
 */
public class App6 {
    public static int count = 0;

    public static void getRe(int[] a, int step) {
        if (step == 12) {
            if (a[0] * a[1] + a[2] * a[3] == a[4] * a[5] && a[6] * a[7] - a[8] * a[9] == a[10] * a[11])
                count++;
            return;
        }
        for (int i = step; i < 12; i++) {
            swap(a, step, i);
            getRe(a, step + 1);
            swap(a, step, i);
        }

    }

    public static void swap(int[] a, int b, int c) {
        int temp = a[b];
        a[b] = a[c];
        a[c] = temp;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        getRe(a, 0);
        for (int i = 0; i < 12; i++) {
            for (int j = i; j < 12; j++) {
                a[j]++;
            }
            getRe(a, 0);
            for (int j = i; j < 12; j++) {
                a[j]--;
            }
        }
        System.out.println(count);
    }
    // answer:122368
}
