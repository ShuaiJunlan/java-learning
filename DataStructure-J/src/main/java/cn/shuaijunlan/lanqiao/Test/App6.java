package cn.shuaijunlan.lanqiao.Test;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 19:49 2017/3/22.
 */
public class App6 {
    public static int conn[][] = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},   //0
            {0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},   //1
            {0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},   //2
            {0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0},   //3
            {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0},   //4
            {0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0},   //5
            {0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0},   //6
            {0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0},   //7
            {0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1},   //8
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0},   //9
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0},   //10
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1},   //11
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0}   //12
    };

    public static boolean isTrue(int[] arr) {
        int[] mark = new int[5];
        mark[0] = 1;
        is(arr, 0, mark);
        int count = 0;
        for (int i : mark) {
            if (i == 1)
                count++;
        }
        if (count == 5)
            return true;
        return false;
    }

    public static void is(int[] arr, int begin, int[] mark) {
        for (int i = 0; i < arr.length; i++) {
            if (mark[i] != 1) {
                if (conn[arr[begin]][arr[i]] == 1) {
                    mark[i] = 1;
                    is(arr, i, mark);
                }
            }
        }
    }

    public static void main(String[] args) {
        int count = 0;
        for (int i = 1; i <= 12; i++)
            for (int j = i + 1; j <= 12; j++)
                for (int k = j + 1; k <= 12; k++)
                    for (int m = k + 1; m <= 12; m++)
                        for (int n = m + 1; n <= 12; n++) {
                            int[] arr = new int[5];
                            arr[0] = i;
                            arr[1] = j;
                            arr[2] = k;
                            arr[3] = m;
                            arr[4] = n;
                            if (isTrue(arr))
                                count++;
                        }
        System.out.println(count);
    }
}
