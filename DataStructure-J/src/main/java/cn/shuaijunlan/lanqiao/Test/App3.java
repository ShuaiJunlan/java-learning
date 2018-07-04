package cn.shuaijunlan.lanqiao.Test;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 17:18 2017/3/22.
 */
public class App3 {
    public static int[] num = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    public static int count = 0;

    public static void swap(int x, int y) {
        int temp = num[x];
        num[x] = num[y];
        num[y] = temp;
    }

    public static void full(int step) {
        if (step == 10) {
            if (isTrue())
                count++;
            return;
        }
        for (int i = step; i < 10; i++) {
            swap(i, step);
            full(step + 1);
            swap(i, step);
        }
    }

    public static boolean isTrue() {
        if (num[0] < num[1] && num[0] < num[2] &&
                num[1] < num[3] && num[1] < num[4] &&
                num[2] < num[4] && num[2] < num[5] &&
                num[3] < num[6] && num[3] < num[7] &&
                num[4] < num[7] && num[4] < num[8] &&
                num[5] < num[8] && num[5] < num[9])
            return true;
        return false;
    }

    public static void main(String[] args) {
        full(0);
        System.out.println(count);
    }


}
