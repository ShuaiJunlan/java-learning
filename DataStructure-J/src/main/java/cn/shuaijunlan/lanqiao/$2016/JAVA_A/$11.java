package cn.shuaijunlan.lanqiao.$2016.JAVA_A;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 21:37 2017/3/3.
 */
public class $11 {
    public static boolean getFour(int[] arr) {
        if (arr == null || arr.length < 7)
            return false;
        int i = 1;
        int j = arr.length - 2;
        int sum = 0;
        int sum_i = arr[0];
        int sum_j = arr[arr.length - 1];
        int sum_i1 = 0;
        for (int a = 0; a < arr.length; a++) {
            sum += arr[a];
        }
        while ((i + 3) < j && (sum - (sum_i << 1) - arr[i] - arr[j]) >= (sum_i << 1))      //  保证i<j && (sum - sum_i<<1 - arr[i] - arr[j] > sum_i<<1)
        {
            while (sum_i != sum_j && (i + 3) < j) {
                if (sum_i < sum_j) {
                    sum_i += arr[i];
                    i++;
                } else {
                    sum_j += arr[j];
                    j--;
                }
            }
            if (sum_i != sum_j)
                return false;
            else {
                int i1 = i + 1;
                sum_i1 = arr[i1];
                i1++;
                while (sum_i1 != sum_i && (i1 + 2) < j) {
                    sum_i1 += arr[i1++];
                }
                if (sum_i1 != sum_i)
                    return false;
                while (sum_i1 == sum_i) {
                    if (sum - arr[i] - arr[i1] - arr[j] == sum_i << 2) {
                        return true;
                    }
                    sum_i1 += arr[i1++];
                }
                sum_i += arr[i];
                i++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = {2, 5, 1, 1, 1, 1, 4, 1, 7, 3, 7};
        int[] b = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        int[] c = {1, 1, 1, 1, 1, 1, 1};
        int[] d = {0, 0, 0, 0, 0, 0, 0};
        int[] e = {10, 2, 11, 13, 1, 1, 1, 1, 1};
        int[] f = {1, 2, 1, 3, 0, 0, 2, 2, 1, 3, 3};
        int[] g = {1, 2, -1, 1, 2, 5, 2, 5, 2};
        System.out.println(getFour(a));
        System.out.println(getFour(b));
        System.out.println(getFour(c));
        System.out.println(getFour(d));
        System.out.println(getFour(e));
        System.out.println(getFour(f));
        System.out.println(getFour(g));
    }
}
