package cn.shuaijunlan.lanqiao.Test;

import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 18:12 2017/3/$23.
 */
public class App_m_2_1 {
    public static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static int[][][][][][] visit = new int[3][3][3][3][3][3];
    public static int a1, a2, b1, b2;      //  a表示A点的坐标，b表示B点的坐标
    public static int min = Integer.MAX_VALUE;

    /**
     * @param x1 表示A的坐标
     * @param y1
     * @param x2 表示B的坐标
     * @param y2
     * @param x3 表示空格的坐标
     * @param y3
     */
    public static void bfs(int x1, int y1, int x2, int y2, int x3, int y3, int step) {
        if (step >= min)
            return;
        if (x1 == b1 && y1 == b2 && x2 == a1 && y2 == a2) {
            min = min < step ? min : step;
            return;
        }
        if (x1 < 0 || x1 > 2 || y1 < 0 || y1 > 1)
            return;
        if (x2 < 0 || x2 > 2 || y2 < 0 || y2 > 1)
            return;
        if (x3 < 0 || x3 > 2 || y3 < 0 || y3 > 1)
            return;
        if (visit[x1][y1][x2][y2][x3][y3] == 1)
            return;
        visit[x1][y1][x2][y2][x3][y3] = 1;
        for (int i = 0; i < 4; i++) {
            int temp_x3 = x3 + dir[i][0], temp_y3 = y3 + dir[i][1];

            if (temp_x3 == x1 && temp_y3 == y1) {
                bfs(x3, y3, x2, y2, x1, y1, step + 1);
            } else if (temp_x3 == x2 && temp_y3 == y2) {
                bfs(x1, y1, x3, y3, x2, y2, step + 1);
            } else {
                bfs(x1, y1, x2, y2, temp_x3, temp_y3, step + 1);
            }
        }
        visit[x1][y1][x2][y2][x3][y3] = 0;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] a = scanner.nextLine().toCharArray();
        char[] b = scanner.nextLine().toCharArray();
        int x1 = 0, y1 = 0, x2 = 0, y2 = 0, x3 = 0, y3 = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 'A') {
                a1 = x1 = i;
                a2 = y1 = 0;
            } else if (a[i] == 'B') {
                b1 = x2 = i;
                b2 = y2 = 0;
            } else if (a[i] == ' ') {
                x3 = i;
                y3 = 0;
            }
        }
        for (int i = 0; i < b.length; i++) {
            if (b[i] == 'A') {
                a1 = x1 = i;
                a2 = y1 = 1;
            } else if (b[i] == 'B') {
                b1 = x2 = i;
                b2 = y2 = 1;
            } else if (b[i] == ' ') {
                x3 = i;
                y3 = 1;
            }
        }
        bfs(x1, y1, x2, y2, x3, y3, 0);
        System.out.println(min);
    }
}