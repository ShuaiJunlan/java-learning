package cn.shuaijunlan.lanqiao.$2016.JAVA_A;

import java.util.Arrays;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 15:26 2017/3/6.
 */
public class $09 {
    public static int exchangeBottle1(int[] arr) {
        if (arr == null && arr.length == 0)
            return 0;
        int count = 0;  //  记录交换次数
        int[] flag = new int[arr.length];   //  记录值value对应的位置
        for (int i = 0; i < arr.length; i++) {
            flag[arr[i]] = i;
        }
        for (int j = 0; j < arr.length; j++) {
            if (j != arr[j]) {
                int x = arr[j];
                arr[j] ^= arr[flag[j]] ^= arr[j] ^= arr[flag[j]];       //  使用抑或实现两个数的交换
                flag[j] ^= flag[x] ^= flag[j] ^= flag[x];
                count++;
            }
        }
        return count;
    }

    public static int exchangeBottle2(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;
        int count = 0;
        int[] flag = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            flag[arr[i]] = i;
        }
        for (int j = 0; j < arr.length; j++) {
            while (arr[j] != j) {
//                arr[j] ^= arr[arr[j]] ^= arr[j] ^= arr[arr[j]];
                swap(arr, j, arr[j]);
                count++;
            }
        }
        return count;
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
//        int[] arr = {2,0,1,4,3,5,7,6,9,8,11,10};
//        int[] arr1 = {2,0,1,4,3};
//        int[] arr2 = {4,3,2,1,0};
//        int[] arr3 = {0,1,2,3,4,5,6,7,8,9};
//        System.out.println(exchangeBottle1(arr1));
//        System.out.println(exchangeBottle2(arr1));

        int flag[] = {0, 1};
        flag[0] ^= flag[1] ^= flag[0] ^= flag[1];
        System.out.println(Arrays.toString(flag));
    }
}
