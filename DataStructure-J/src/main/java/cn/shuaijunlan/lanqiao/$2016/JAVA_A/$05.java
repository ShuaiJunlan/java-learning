package cn.shuaijunlan.lanqiao.$2016.JAVA_A;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 17:30 2017/3/8.
 */
public class $05 {
    public static int count = 0;

    public static void f(int[] a, int k, int n, String s) {
        if (k == a.length) {
            if (n == 0) {
                System.out.println(s);
                count++;
            }
            return;
        }

        String s2 = s;
        for (int i = 0; i <= a[k]; i++) {
            f(a, k + 1, n - i, s2);
            s2 += (char) (k + 'A');
        }
    }

    public static void main(String[] args) {
        int[] a = {4, 2, 2, 1, 1, 3};

        f(a, 0, 5, "");
        System.out.println($05.count);
    }
}
