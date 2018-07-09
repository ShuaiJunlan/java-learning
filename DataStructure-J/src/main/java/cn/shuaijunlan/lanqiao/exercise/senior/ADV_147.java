package cn.shuaijunlan.lanqiao.exercise.senior;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 17:08 2017/3/12.
 */
public class ADV_147 {
    private static char[][] matrix = null;      //  用来存放迷宫的数组

    private static int count = Integer.MAX_VALUE;
    private static int n = 0;                   //  迷宫的行数
    private static int m = 0;                   //  迷宫的列数
    private static String str = "X";

    /**
     * 方法一：
     * 获取最短走出迷宫路径，采用递归的方式，效率低，超时</br>
     * 分析：
     * 1.字符串拼接效率低
     * 2.
     *
     * @param x         起点x坐标
     * @param y         起点y坐标
     * @param step      行走的步数
     * @param direction 上一步行走的方向
     * @param s         已经走过的路线
     */
    public static void getMinDis(int x, int y, int step, char direction, String s) {
        if (x == (m - 1) && y == n && step <= count) {
            count = step < count ? step : count;
            str = s.compareTo(str) < 0 ? s : str;
        } else if (step >= (n * (n - 1)) * 2)
            return;
        else if (step < count) {
            if (x - 1 >= 0 && matrix[y][x - 1] == '0' && direction != 'R') //  向左走
                getMinDis(x - 1, y, step + 1, 'L', s + "L");

            if (x + 1 <= (m - 1) && matrix[y][x + 1] == '0' && direction != 'L') //  向右走
                getMinDis(x + 1, y, step + 1, 'R', s + "R");

            if (y - 1 >= 1 && matrix[y - 1][x] == '0' && direction != 'D') //  向上走
                getMinDis(x, y - 1, step + 1, 'U', s + "U");

            if (y + 1 <= n && matrix[y + 1][x] == '0' && direction != 'U') //  向下走
                getMinDis(x, y + 1, step + 1, 'D', s + "D");
        } else
            return;
    }


    private static int[][] visit = new int[550][550];    //  记录判断是否访问过，默认为0,已访问过表示1
    private static final int[][] direction = {{0, 1}, {-1, 0}, {1, 0}, {0, -1}}; //  表示四个方向
    private static int[][] pre = new int[550][550];             //  保存上一步到这步走的方向
    private static final char[] dir = {'D', 'L', 'R', 'U'};        //  和direction一一对应

    /**
     * 静态内部类，用来表示每个行走的节点
     */
    static class Node {
        int x;  //  横坐标
        int y;  //  纵坐标
        int sum;    //  从起点到该点已经行走的步数

        Node(int x, int y, int sum) {
            this.x = x;
            this.y = y;
            this.sum = sum;
        }
    }

    /**
     * 方法二：
     * 采用一个队列来保存下一步可能走的方向。
     */
    public static void dfs() {
        Queue<Node> queue = new LinkedList<Node>();
        Node node = new Node(0, 1, 0);
        visit[1][0] = 1;
        pre[1][0] = -1;
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node node1 = queue.poll();
            if (node1.x == (m - 1) && node1.y == n) {
                System.out.println(node1.sum);
                return;
            }
            for (int i = 0; i < 4; i++) {
                Node tempNode = new Node(0, 0, 0);

                tempNode.x = node1.x + direction[i][0];
                tempNode.y = node1.y + direction[i][1];
                tempNode.sum = node1.sum + 1;
                if (tempNode.x >= 0 && tempNode.x <= (m - 1) && tempNode.y >= 1 && tempNode.y <= n && matrix[tempNode.y][tempNode.x] == '0' && visit[tempNode.y][tempNode.x] != 1) {
                    visit[tempNode.y][tempNode.x] = 1;
                    queue.offer(tempNode);
                    pre[tempNode.y][tempNode.x] = i;
                }
            }
        }
    }

    /**
     * 回溯打印路径
     *
     * @param x
     * @param y
     */
    public static void printPath(int x, int y) {
        if (x == 0 && y == 1)
            return;
        printPath(x - direction[pre[y][x]][0], y - direction[pre[y][x]][1]);
        System.out.print(dir[pre[y][x]]);
    }

    /**
     * 测试函数
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        matrix = new char[n + 1][m];
        for (int i = 0; i <= n; i++) {
            String str = scanner.nextLine();
            matrix[i] = str.toCharArray();
        }
        /*  测试一 */
        String s = new String();
        getMinDis(0, 1, 0, ' ', s);
        System.out.println(count);
        System.out.print(str);

        /*  测试二 */
        dfs();
        printPath(m - 1, n);
    }
}
