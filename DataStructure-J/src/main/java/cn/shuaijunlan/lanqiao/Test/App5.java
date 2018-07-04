package cn.shuaijunlan.lanqiao.Test;


import java.util.Arrays;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 20:19 2017/3/22.
 */
public class App5 {
    public static int[] arr = new int[12];
    public static int count = 0;

    public static boolean isTrue() {
        if (arr[0] + arr[1] == arr[2] && arr[3] - arr[4] == arr[5] && arr[6] * arr[7] == arr[8] && arr[10] * arr[11] == arr[9])
            return true;
        return false;
    }

    public static void isFull(int step) {
        if (step == 12) {
            if (isTrue()) {
                count++;
                System.out.println(Arrays.toString(arr));
            }
        }
        for (int i = step; i < 12; i++) {
            swap(arr, step, i);
            isFull(step + 1);
            swap(arr, step, i);
        }
    }

    public static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        arr = arr1;
        isFull(0);
        for (int i = 0; i < 12; i++) {
            for (int j = i; j < 12; j++) {
                arr1[j]++;
            }
            arr = arr1;
            isFull(0);
//            System.out.println(Arrays.toString(arr));
            for (int m = 0; m < 12; m++) {
                arr1[m] = m + 1;
            }
        }
        System.out.println(count);
    }


}
