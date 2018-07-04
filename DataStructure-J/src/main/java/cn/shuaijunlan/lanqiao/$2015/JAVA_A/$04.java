package cn.shuaijunlan.lanqiao.$2015.JAVA_A;

import java.util.Vector;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 17:20 2017/3/13.
 */
public class $04 {
    public static int f(int n, int m) {
        n = n % m;
        Vector v = new Vector();

        for (; ; ) {
            v.add(n);
            n *= 10;
            n = n % m;
            if (n == 0) return 0;
            if (v.indexOf(n) >= 0)
                return v.size() - v.indexOf(n);  //填空
        }
    }

    public static void main(String[] args) {
        System.out.println(f(11, 13));
    }
}
