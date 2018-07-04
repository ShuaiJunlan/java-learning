package cn.shuaijunlan.lanqiao.simu5;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 16:21 2017/4/1.
 */
public class App9 {
    static class Position {
        int x;
        int y;
        int visit;

        Position(int x, int y, int visit) {
            this.x = x;
            this.y = y;
            this.visit = visit;
        }
    }

    private static LinkedList<Position> linkedPosition = new LinkedList<>();    //  记录炸弹位置。
    private static LinkedList<Position>[] posX = null;
    private static LinkedList<Position>[] posY = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        posX = new LinkedList[m + 1];
        posY = new LinkedList[n + 1];
        for (int i = 0; i < n; i++) {
            char[] str = scanner.nextLine().toCharArray();
            for (int j = 0; j < str.length; j++) {
                if (str[j] == '1') {
                    Position position = new Position(j + 1, i + 1, 0);
                    linkedPosition.add(position);
                    posX[j + 1] = new LinkedList<>();
                    posY[i + 1] = new LinkedList<>();
                    posX[j + 1].add(position);
                    posY[i + 1].add(position);
                }
            }
        }
    }
}
