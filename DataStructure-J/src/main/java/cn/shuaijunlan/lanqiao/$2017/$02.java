package cn.shuaijunlan.lanqiao.$2017;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 20:15 2017/3/16.
 */
public class $02 {
    static int n;
    static int[] a = new int[6];

    static boolean pd() {
        int p[] = new int[20];
        for (int i = 0; i < 6; i++) {
            int k = 0;
            while (a[i] != 0) {
                p[a[i] % 10]++;
                if (p[a[i] % 10] > 2) return false;
                a[i] /= 10;
                k++;
            }
            if (k < 3) return false;
        }
        return true;
    }

    public static void main(String[] args) {
//        for (int i = 100; i <= 999; i++)
//            for (int j = 100; j <= 999; j++)
//            {
//                a[0] = (j % 10) * i;
//                a[1] = (j / 10 % 10) * i;  //a保存每一个状态的信息
//                a[2] = (j / 100) * i;
//                a[3] = i * j;
//                a[4] = i;
//                a[5] = j;
//                if (pd())
//                {  //判断是否满足条件
//                    System.out.println(i * j);
//                }
//            }
        for (int i = 100; i < 999; i++)
            for (int j = 100; j < 999; j++) {
                char[] a = String.valueOf(i).toCharArray();
                char[] b = String.valueOf(j).toCharArray();

                char[] a1 = String.valueOf((b[0] - '0') * i).toCharArray();
                char[] a2 = String.valueOf((b[1] - '0') * i).toCharArray();
                char[] a3 = String.valueOf((b[2] - '0') * i).toCharArray();

                char[] result = String.valueOf(i * j).toCharArray();

                if (checkResult(a, b, a1, a2, a3, result))
                    System.out.println(Integer.parseInt(String.valueOf(result)));
            }
        /**
         * Answer:40096;
         */


    }

    public static boolean checkResult(char[] a, char[] b, char[] c, char[] d, char[] e, char[] f) {
        int[] r = new int[10];
        for (int i = 0; i < a.length; i++) {
            r[a[i] - '0']++;
        }
        for (int i = 0; i < b.length; i++) {
            r[b[i] - '0']++;
        }
        for (int i = 0; i < c.length; i++) {
            r[c[i] - '0']++;
        }
        for (int i = 0; i < d.length; i++) {
            r[d[i] - '0']++;
        }
        for (int i = 0; i < e.length; i++) {
            r[e[i] - '0']++;
        }
        for (int i = 0; i < f.length; i++) {
            r[f[i] - '0']++;
        }
        for (int i = 0; i < r.length; i++) {
            if (r[i] != 2)
                return false;
        }
        return true;
    }
}
