package cn.shuaijunlan.lanqiao.$2015.CH_JAVA_A;


/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 20:25 2017/3/10.
 */
public class Test2 {
    public static int count = 0;
    public static long b = 0;
    public static int t0, t1, t2, t3, t4, t5, t6, t7, t8, t9;

    public static void swap(int[] arr, int a, int b) {
        int m = arr[a];
        arr[a] = arr[b];
        arr[b] = m;
    }

    public static void getRe(int[] arr, int step) {
        if (step == 15) {
            t1 = 1 + arr[0] + arr[1] + arr[2];
            t2 = 1 + arr[3] + arr[7] + arr[11];
            if (t1 != t2 || t1 != 34 || t2 != 34)
                return;
//            System.out.println(++b);
            t0 = arr[2] + arr[5] + arr[8] + arr[11];

            t3 = 1 + arr[4] + arr[9] + arr[14];

            t4 = arr[3] + arr[4] + arr[5] + arr[6];
            t5 = arr[7] + arr[8] + arr[9] + arr[10];
            t6 = arr[11] + arr[12] + arr[13] + arr[14];

            t7 = arr[0] + arr[4] + arr[8] + arr[12];
            t8 = arr[1] + arr[5] + arr[9] + arr[13];
            t9 = arr[2] + arr[6] + arr[10] + arr[14];
            if (t0 == t1 && t1 == t2 && t2 == t3 && t3 == t4 && t4 == t5 && t5 == t6 && t6 == t7 && t7 == t8 && t8 == t9) {
                count++;
                System.out.println(count);
            }

        }
        for (int i = step; i < arr.length; i++) {
            swap(arr, i, step);
            getRe(arr, step + 1);
            swap(arr, i, step);
        }

    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        getRe(arr, 0);
        System.out.println(count);

    }

}
