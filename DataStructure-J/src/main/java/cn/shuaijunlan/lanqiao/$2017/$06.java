package cn.shuaijunlan.lanqiao.$2017;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 15:34 2017/3/18.
 */
public class $06 {
    /**
     * 一共有二十四种滑动方向
     */
    public static int[][] directions1 = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1},
            {-1, 1}, {1, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};
    public static int[][] directions2 = {{1, 1}, {2, 2}, {-1, -1}, {-2, -2}, {-1, 1}, {-2, 2}, {1, -1}, {2, -2},
            {0, 1}, {0, 2}, {0, -1}, {0, -2}, {1, 0}, {2, 0}, {-1, 0}, {-2, 0}};
    public static int[][] visit;
    public static int[] ans = new int[10];
    public static int min_steps, n, sum;

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static Node[] nodes;

    public static void dfs(int x, int y, int step) {
        if (step >= min_steps) {
            int temp = 0;
            for (int i = 0; i < step; i++) {
                for (int j = 1; j <= n; j++) {
                    if ((nodes[j].x == ans[i] && nodes[j].y == ans[i + 1]) || (nodes[j].y == ans[i] && nodes[j].x == ans[i + 1]))
                        temp++;
                }
            }
            if (temp >= n)
                sum++;
        }
        /**
         * 其中的16种可能的方向
         */
        for (int i = 0; i < 16; i++) {
            int x1 = x + directions1[i][0], y1 = y + directions1[i][1];     //  赋值给一个新的变量
            if (x1 >= 1 && x1 <= 3 && y1 >= 1 && y1 <= 3 && visit[y1][x1] == 0) {
                visit[y1][x1] = 1;
                ans[step] = x1 + 3 * (y1 - 1);
                dfs(x1, y1, step + 1);
                visit[y1][x1] = 0;
            }

        }
        /**
         * 其中8种可能的方向
         */
        for (int i = 0; i < 16; i += 2) {
            int x1 = x + directions2[i][0];
            int y1 = y + directions2[i][1];
            int x2 = x + directions2[i + 1][0];
            int y2 = y + directions2[i + 1][1];
            if (visit[y1][x1] == 1 && x2 >= 1 && x2 <= 3 && y2 >= 1 && y2 <= 3 && visit[y2][x2] == 0) {
                visit[y2][x2] = 1;
                ans[step] = x + 3 * (y - 1);
                dfs(x2, y2, step + 1);
                visit[y2][x2] = 0;
            }
        }
    }

    public static void main(String[] args) {
        HashSet<Integer> hashSet = new HashSet<>();
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            int x = scanner.nextInt(), y = scanner.nextInt();
            hashSet.add(x);
            hashSet.add(y);
            nodes[i] = new Node(x, y);
        }
        visit = new int[4][4];

        min_steps = Math.max(4, hashSet.size());
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                visit[j][i] = 1;
                ans[0] = (j - 1) * 3 + i;
                dfs(i, j, 1);
                visit[j][i] = 0;
            }
        }
        System.out.println(sum);
    }
}