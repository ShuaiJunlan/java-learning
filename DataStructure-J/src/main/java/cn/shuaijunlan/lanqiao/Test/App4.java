package cn.shuaijunlan.lanqiao.Test;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 10:15 2017/3/$23.
 */
public class App4 {
    public static int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    public static int count = 0;

    public static void full(int step) {
        if (step == 10) {
            if (isTrue())
                count++;
        }
        for (int i = step; i < 10; i++) {
            swap(step, i);
            full(step + 1);
            swap(step, i);
        }
    }

    public static void swap(int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static boolean isTrue() {
        if (isOne(0, 1) && isOne(0, 4) && isOne(0, 5) && isOne(0, 3) &&
                isOne(1, 0) && isOne(1, 2) && isOne(1, 4) && isOne(1, 5) && isOne(1, 6) &&
                isOne(2, 1) && isOne(2, 5) && isOne(2, 6) &&
                isOne(3, 0) && isOne(3, 4) && isOne(3, 8) && isOne(3, 7) &&
                isOne(4, 0) && isOne(4, 1) && isOne(4, 3) && isOne(4, 5) && isOne(4, 7) && isOne(4, 8) && isOne(4, 9) &&
                isOne(5, 0) && isOne(5, 1) && isOne(5, 2) && isOne(5, 4) && isOne(5, 6) && isOne(5, 8) && isOne(5, 9) &&
                isOne(6, 1) && isOne(6, 2) && isOne(6, 5) && isOne(6, 9) &&
                isOne(7, 3) && isOne(7, 4) && isOne(7, 8) &&
                isOne(8, 3) && isOne(8, 4) && isOne(8, 5) && isOne(8, 7) && isOne(8, 9) &&
                isOne(9, 4) && isOne(9, 5) && isOne(9, 6) && isOne(9, 8))
            return true;
        return false;
    }

    public static boolean isOne(int a, int b) {
        if (Math.abs(arr[a] - arr[b]) == 1)
            return false;
        return true;
    }

    public static void main(String[] args) {
        full(0);
        System.out.println(count);
    }
}
