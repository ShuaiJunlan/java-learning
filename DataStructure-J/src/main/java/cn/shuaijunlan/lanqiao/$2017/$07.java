package cn.shuaijunlan.lanqiao.$2017;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 11:34 2017/3/18.
 */
public class $07 {
    public static int[][] edges;
    public static int[] connector;

    /**
     * 求并查集，通过此函数可以判断两个点是否连通
     * 归并实现
     *
     * @param a
     * @return
     */
    public static int getPoint(int a) {
        if (connector[a] == a)
            return a;
        return getPoint(connector[a]);
    }

    /**
     * 非递归实现并查集。
     *
     * @param a
     * @return
     */
    public static int getPoint1(int a) {
        int t = a;
        while (connector[t] != t)
            t = connector[t];
        return t;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        edges = new int[m + 1][2];
        connector = new int[n + 1];
        HashSet<Integer> hashSet = new HashSet<>();


        for (int i = 1; i <= m; i++)        //  输入边
        {
            edges[i][0] = scanner.nextInt();
            edges[i][1] = scanner.nextInt();
        }
        int p = scanner.nextInt();          //  起点
        int q = scanner.nextInt();          //  终点
        hashSet.add(p);
        hashSet.add(q);
        for (int i = 1; i <= m; i++) {
            for (int k = 1; k <= n; k++)       //  初始化
            {
                connector[k] = k;
            }
            for (int j = 1; j <= m; j++) {
                if (i != j) {
                    int a = getPoint(edges[j][0]);
                    int b = getPoint(edges[j][1]);
                    connector[a] = b;
                }
            }
            if (getPoint(p) != getPoint(q)) {
                hashSet.add(edges[i][0]);
                hashSet.add(edges[i][1]);
            }
        }
        System.out.println(hashSet.size() - 2);
    }
}
