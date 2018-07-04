package cn.shuaijunlan.lanqiao.$2016.JAVA_A;

import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 19:32 2017/3/6.
 */
public class $08 {
    static int[] canGet = new int[3];
    static int[][][] cache;//=new int[2][1000][1000];


    public static int dfs(int who, int left, int a, int b) {
        if (left < canGet[0] && left < canGet[1] && left < canGet[2]) {
            if (who == 0) {
                if (a % 2 == 1 && b % 2 == 0) {
                    return 1;
                }
                if (a % 2 == 1 && b % 2 == 1 || (b % 2 == 0 && a % 2 == 0)) {
                    return 0;
                }
            }
            if (who == 1) {
                if (b % 2 == 1 && a % 2 == 0) {
                    return 1;
                }
                if ((b % 2 == 1 && a % 2 == 1) || (b % 2 == 0 && a % 2 == 0)) {
                    return 0;
                }
            }
            return -1;
        }
        if (cache[who][a][b] != -999) {
            return cache[who][a][b];
        }
        int canWin = -999;
        for (int i = 0; i < canGet.length; i++) {
            if (left - canGet[i] >= 0) {
                if (who == 0) {
                    canWin = Math.max(canWin, -dfs(1 - who, left - canGet[i], a + canGet[i], b));
                } else {
                    canWin = Math.max(canWin, -dfs(1 - who, left - canGet[i], a, b + canGet[i]));
                }
            }
        }
        cache[who][a][b] = canWin;
        return canWin;
    }

    //    public static int[] can = new int[3];
    public static int[] nums = new int[5];

    public static int getWinByFirst(int who, int left, int a, int b) {
        if (left < canGet[0] && left < canGet[1] && left < canGet[2]) {
            if (who == 0) {
                if (a % 2 == 1 && b % 2 == 0) {
                    return 1;
                } else if ((a % 2 == 0 && b % 2 == 0) || (a % 2 == 1 && b % 2 == 1)) {
                    return 0;
                } else if (a % 2 == 0 && b % 2 == 1) {
                    return -1;
                }
            } else {
                if (a % 2 == 1 && b % 2 == 0) {
                    return -1;
                } else if ((a % 2 == 0 && b % 2 == 0) || (a % 2 == 1 && b % 2 == 1)) {
                    return 0;
                } else if (a % 2 == 0 && b % 2 == 1) {
                    return 1;
                }
            }
        }
        for (int j = 0; j < canGet.length; j++) {
            if (who == 0) {
                getWinByFirst(1 - who, left - canGet[j], a + canGet[j], b);
            } else {
                getWinByFirst(1 - who, left - canGet[j], a, b + canGet[j]);
            }
        }
        return 0;
    }

    public static int[] all = new int[1000];
    public static int[] can = new int[3];
    public static char[] types = {'-', '0', '0', '+'}; // 分别表示A:B为偶奇、奇奇、偶偶、奇偶

    public static void setResult() {
        int min = Math.min(can[0], Math.min(can[1], can[2]));
        for (int s = 0; s < min; s++) {
            all[s] = 2;
        }
        for (int i = min; i < all.length; i++) {
            int temp = 0;       //  记录博弈结果
            for (int j = 0; j < can.length; j++) {
                int left = i - can[j];
                if (left < 0)
                    continue;
                else if (all[left] == 3) {
                    if (isOdd(can[j]))
                        temp = (1 > temp) ? 1 : temp;
                } else if (all[left] == 2) {
                    if (isOdd(can[j]))
                        temp = 3;
                    else
                        temp = (2 > temp) ? 2 : temp;
                } else if (all[left] == 1) {
                    if (!isOdd(can[j]))
                        temp = (1 > temp) ? 1 : temp;      //  ???
                } else if (all[left] == 0) {
                    if (isOdd(can[j]))
                        temp = (2 > temp) ? 2 : temp;
                    else
                        temp = 3;
                }

            }
            all[i] = temp;
        }
    }

    public static boolean isOdd(int num) {
        if (num % 2 == 0)
            return false;
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            can[i] = sc.nextInt();
        }
        setResult();
        for (int k = 0; k < 5; k++) {
            int num = sc.nextInt();
            System.out.print(types[all[num]]);
            if (k != 4)
                System.out.print(" ");
//            cache = new int[2][num + 1][num + 1];
//            for (int i = 0; i < 2; i++)
//            {
//                for (int j = 0; j < num + 1; j++)
//                {
//                    for (int j2 = 0; j2 < num + 1; j2++)
//                    {
//                        cache[i][j][j2] = -999;
//                    }
//                }
//            }
//            int ans = dfs(0, num, 0, 0);
//            if (ans == 1)
//            {
//                System.out.print("+");
//            }
//            if (ans == -1)
//            {
//                System.out.print("-");
//            }
//            if (ans == 0)
//            {
//                System.out.print("0");
//            }
//            if (k != 4)
//            {
//                System.out.print(" ");
//            }
        }
        System.out.println();
    }

}
