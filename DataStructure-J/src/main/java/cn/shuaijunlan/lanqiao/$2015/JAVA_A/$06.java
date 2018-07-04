package cn.shuaijunlan.lanqiao.$2015.JAVA_A;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 21:49 2017/3/1.
 */
public class $06 {
    public static int doSum(int[] arr, int i, int j) {
        int sum = 0;
        for (int k = 0; k < arr.length; k++) {
            if (k == arr.length - 1) {
                sum += arr[k];
            } else if (k == i || k == j) {
                sum = sum + arr[k] * arr[k + 1];
                k += 1;
            } else
                sum += arr[k];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = new int[49];
        for (int l = 1; l < 50; l++)
            arr[l - 1] = l;
        for (int i = 0; i < 48; i++) {
            for (int j = i + 2; j < 48; j++) {
//                System.out.println(doSum(arr, i, j));
                if (doSum(arr, i, j) == 2015) {
                    System.out.println(i + " " + j);
                    break;
                }
            }
        }
    }
}
