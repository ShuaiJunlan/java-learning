package cn.shuaijunlan.lanqiao.$2016.JAVA_A;


/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 10:58 2017/3/7.
 */
public class $07 {
    public static int count = 0;
    static int cc[][] = {
            {1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0},
            {0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1},
    };

    public static void getAll() {
        for (int i = 0; i < 12; i++) {
            for (int j = i + 1; j < 12; j++) {
                for (int k = j + 1; k < 12; k++) {
                    for (int m = k + 1; m < 12; m++) {
                        for (int n = m + 1; n < 12; n++) {
                            int[] aa = {i, j, k, m, n};
                            if (isA(aa))
                                count++;

                        }
                    }
                }
            }
        }
    }

    public static boolean isA(int[] a) {
//        int[] mark = new int[5];
//        mark[0] = 1;
//        isM(a, 0, mark);
//        int i = 0;
//        for (; i < mark.length; i ++ )                //  天大的bug！！！！！！！！！！！！！！
//        {
//            if (mark[i] != 1)
//            {
//                break;
//            }
//        }
//        if (i == 4)
//            return true;
//        return false;

        int mark[] = new int[5];
        mark[0] = 1;
        isM(a, 0, mark);
        int c = 0;
        for (int i : mark) {
            if (i == 1)
                c++;
        }
        if (c == 5)
            return true;
        return false;
    }

    public static void isM(int[] a, int s, int[] mark) {
        for (int i = 0; i < a.length; i++) {
            if (mark[i] == 0) {
                if (cc[a[s]][a[i]] == 1)            //  不能简单的通过+1 -1 +4 -4 来判断是否相邻。
                {
                    mark[i] = 1;
                    isM(a, i, mark);                //  深度搜索
                }
            }
        }
    }

    public static void main(String[] args) {
        getAll();
        System.out.println($07.count);
    }

}
