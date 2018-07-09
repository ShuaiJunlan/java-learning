package cn.shuaijunlan.nowcoder.$2018;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 20:50 2017/4/27.
 */

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 19:48 2017/4/27.
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 19:48 2017/4/27.
 */
/*
5
R 0 0 0
R 0 4 0
R 0 0 3
G 92 14 7
G 12 16 8
 */

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 19:48 2017/4/27.
 */
/*
public class Test
{
    private static int[][] point;
    private static int n;
    private static char[] color;
    private static int[] mark;
    private static double val = Double.MIN_VALUE;
    public static boolean isColor(int[] a)
    {
        if (color[a[0]] == color[a[1]] && color[a[1]] == color[a[2]])
            return true;
        else if (color[a[0]] != color[a[1]] && color[a[1]] != color[a[2]] && color[a[0]] != color[a[2]])
            return true;
        return false;
    }
    public static double getDistance(int[] x, int[] y)
    {
        return Math.sqrt((x[0] - y[0]) * (x[0] - y[0]) + (x[1] - y[1]) * (x[1] - y[1]) + (x[2] - y[2]) * (x[2] - y[2]));
    }
    public static double getMianji(double a, double b, double c)
    {
        double p = (a+b+c)/2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        scanner.nextLine();
        color = new char[n];
        point  = new int[n][3];
        mark = new int[n];
        for (int i = 0; i < n; i++)
        {
            String[] str = scanner.nextLine().split(" ");
            color[i] = str[0].toCharArray()[0];
            point[i][0] = Integer.valueOf(str[1]);
            point[i][1] = Integer.valueOf(str[2]);
            point[i][2] = Integer.valueOf(str[3]);
        }
        dfs(0, 0);
        System.out.println(new DecimalFormat("#.00000").format(val));
    }
    public static void dfs(int step, int m)
    {
        if (step == 3)
        {
            int[] temp = new int[3];
            int k = 0;
            for (int i = 0; i < n; i++)
            {
                if (mark[i] == 1)
                    temp[k++] = i;
            }
            if (!isColor(temp))
                return;
            double a = getDistance(point[temp[0]], point[temp[1]]);
            double b = getDistance(point[temp[1]], point[temp[2]]);
            double c = getDistance(point[temp[0]], point[temp[2]]);
            double mianji = getMianji(a, b, c);
            val = val > mianji? val : mianji;
            return;
        }
        for (int i = m; i < n; i++)
        {
            if (mark[i] == 0)
            {
                mark[i] = 1;
                dfs(step+1, i);
                mark[i] = 0;
            }
        }
    }
}
*/