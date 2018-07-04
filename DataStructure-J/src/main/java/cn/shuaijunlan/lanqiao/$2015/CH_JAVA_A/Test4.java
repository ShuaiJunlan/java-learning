package cn.shuaijunlan.lanqiao.$2015.CH_JAVA_A;

import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 15:16 2017/3/12.
 */
public class Test4 {
    private static int count = Integer.MAX_VALUE;
    private static int n = 0;
    private static String[][] matrix = null;

    public static void getMinDis(int x, int y, int step, char direction) {
        if (matrix[y][x].equals("B") && step < count) {
            count = step < count ? step : count;
            System.out.println(count);
        } else if (step >= (n * (n - 1)) * 2)
            return;
        else if (step < count) {
            if (x - 1 >= 0 && !matrix[y][x - 1].equals(matrix[y][x]) && direction != 'r') //  向左走
                getMinDis(x - 1, y, step + 1, 'l');

            if (x + 1 <= (n - 1) && !matrix[y][x + 1].equals(matrix[y][x]) && direction != 'l') //  向右走
                getMinDis(x + 1, y, step + 1, 'r');

            if (y - 1 >= 1 && !matrix[y - 1][x].equals(matrix[y][x]) && direction != 'b') //  向上走
                getMinDis(x, y - 1, step + 1, 't');

            if (y + 1 <= n && !matrix[y + 1][x].equals(matrix[y][x]) && direction != 't') //  向下走
                getMinDis(x, y + 1, step + 1, 'b');
        } else
            return;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        matrix = new String[n + 1][n];
        for (int i = 0; i <= n; i++) {
            String str = scanner.nextLine();
            matrix[i] = str.split(" ");
        }
        getMinDis(0, 1, 0, ' ');
        System.out.println(count);
    }
}
