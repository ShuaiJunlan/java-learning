package cn.shuaijunlan.lanqiao.$2015.JAVA_A;

import java.util.HashSet;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 20:38 2017/3/1.
 */
public class $03 {
    private static HashSet<String> result = new HashSet<String>();

    public void _9(int[] num, int begin) {
        if (begin == 8 && checkSum(num)) {
            result.add(num[0] + num[1] + num[2] + "");
            printArr(num);
        }
        for (int i = begin; i < num.length; i++) {
            swap(num, begin, i);
            _9(num, begin + 1);
            swap(num, begin, i);
        }
    }

    public void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public boolean checkSum(int[] arr) {
        if ((arr[2] + arr[1] * 10 + arr[0] * 100) * 5 == (arr[5] + arr[8] + (arr[4] + arr[7]) * 10 + (arr[3] + arr[6]) * 100))
            return true;
        return false;
    }

    public void printArr(int[] arr) {

        System.out.println("" + arr[0] + arr[1] + arr[2] + " " + arr[3] + arr[4] + arr[5] + " " + arr[6] + arr[7] + arr[8]);
        System.out.println();
    }

    public static void main(String[] args) {
        int[] num = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        $03 o = new $03();
        o._9(num, 0);
    }
}
