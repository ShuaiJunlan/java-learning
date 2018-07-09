package cn.shuaijunlan.lanqiao.$2016.JAVA_A;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 16:31 2017/3/8.
 */
public class $06 {
    public static int count = 0;

    public static void numIsM(int[] arr, int a) {
        if (a == 11) {


            if (arr[0] + arr[1] == arr[2] && arr[3] - arr[4] == arr[5] && arr[6] * arr[7] == arr[8] && arr[10] * arr[11] == arr[9]) {
                count++;
            }

        } else {
            for (int i = a; i < arr.length; i++) {
                swap(arr, a, i);
                numIsM(arr, a + 1);
                swap(arr, a, i);
            }
        }
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args)      //  效率太低，可以简化为9选8进行全排列
    {
        int[] a1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int[] a2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13};
        numIsM(a1, 0);
        for (int i = a1.length - 1; i >= 0; i--) {
            for (int j = a1.length - i; j > 0; j--) {
                a1[a1.length - j] += 1;
            }
            numIsM(a1, 0);
            for (int j = a1.length - i; j > 0; j--) {
                a1[a1.length - j] -= 1;
            }
        }
        System.out.println($06.count);
    }

}
