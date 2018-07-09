package cn.shuaijunlan.lanqiao.simu5;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 15:36 2017/4/1.
 */
public class App3 {
    public static int re = 0;

    public static void fullPermu(int[] arr, int step) {
        if (step == 9) {
            if (arr[0] + arr[1] + arr[2] == arr[3] + arr[4] + arr[5] && arr[6] + arr[7] + arr[8] == arr[3] + arr[4] + arr[5]
                    && arr[0] + arr[3] + arr[6] == arr[1] + arr[4] + arr[7] && arr[1] + arr[4] + arr[7] == arr[2] + arr[5] + arr[8])
                re++;
            return;
        }
        for (int i = step; i < arr.length; i++) {
            swap(i, step, arr);
            fullPermu(arr, step + 1);
            swap(i, step, arr);
        }
    }

    public static void swap(int a, int b, int[] arr) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        fullPermu(arr, 0);
        System.out.println(re);
        //  answer:72
    }
}
