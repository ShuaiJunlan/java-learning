package cn.shuaijunlan.nowcoder.$2017.$04.$11;

import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 20:11 2017/4/11.
 */
public class App2 {
    public static int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    public static char[] position = {'U', 'D', 'L', 'R'};
    public static int min = Integer.MAX_VALUE;
    private static char[][] map = new char[3][3];

    public static void bfs(int x, int y, int step, char posi) {
        if (step >= min)
            return;
        if (getRe(map)) {
            min = min < step ? min : step;
            return;
        }
        for (int i = 0; i < 4; i++) {
            int temp_x3 = x + dir[i][0], temp_y3 = y + dir[i][1];
            int temp = -1;
            if (posi == 'L')
                temp = 3;
            else if (posi == 'R')
                temp = 2;
            else if (posi == 'U')
                temp = 1;
            else if (posi == 'D')
                temp = 0;
            if (i != temp && temp_x3 >= 0 && temp_x3 <= 2 && temp_y3 >= 0 && temp_y3 <= 2) {
                swap(x, y, temp_x3, temp_y3);
                bfs(temp_x3, temp_y3, step + 1, position[i]);
                swap(x, y, temp_x3, temp_y3);
            }
        }
    }

    public static boolean getRe(char[][] a) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 2 && j == 2) {
                    return a[i][j] == '0';
                } else if (j != 2 && a[i][j] - '0' != (i * 3 + j + 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void swap(int x1, int y1, int x2, int y2) {
        char temp = map[x1][y1];
        map[x1][y1] = map[x2][y2];
        map[x2][y2] = temp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int x = 0, y = 0;
        for (int i = 0; i < 3; i++) {
            String[] str = scanner.nextLine().split(" ");
            for (int j = 0; j < 3; j++) {
                map[i][j] = str[i].charAt(0);
                if (map[i][j] == '0') {
                    x = j;
                    y = i;
                }
            }
        }
        bfs(x, y, 0, ' ');
        System.out.println(min);
    }
}
