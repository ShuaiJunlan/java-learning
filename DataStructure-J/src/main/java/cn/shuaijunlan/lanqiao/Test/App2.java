package cn.shuaijunlan.lanqiao.Test;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 17:08 2017/3/22.
 */
public class App2 {
    public static int[] num = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    public static int count = 0;

    public static void full(int step) {
        if (step == 9) {
            if (isTrue())
                count++;
            return;
        }
        for (int i = step; i < 9; i++) {
            swap(step, i);
            full(step + 1);
            swap(step, i);
        }
    }

    public static void swap(int x, int y) {
        int temp = num[x];
        num[x] = num[y];
        num[y] = temp;
    }

    public static boolean isTrue() {
        if (num[0] + (float) num[1] / num[2] + (float) (num[3] * 100 + num[4] * 10 + num[5]) / (num[6] * 100 + num[7] * 10 + num[8]) == 10.0)
            return true;
        return false;
    }

    public static void main(String[] args) {
        full(0);
        System.out.println(count);
    }
}
