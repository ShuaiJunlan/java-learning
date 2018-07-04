package cn.shuaijunlan.lanqiao.simu5;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 16:04 2017/4/4.
 */

/**
 * 01背包问题
 */
public class App7 {
    public static void main(String[] args) {
        int[] re = new int[101];
        int[][] box = new int[][]{{11, 3}, {8, 12}, {11, 17}, {16, 13}, {1, 14}, {2, 8}, {6, 10}, {10, 18}, {17, 11}, {10, 15}
                , {6, 14}, {5, 6}, {2, 19}, {19, 10}, {4, 9}, {7, 9}, {5, 14}, {5, 20}, {15, 19}, {3, 17}, {15, 11}, {7, 25}, {11, 20}
                , {9, 12}, {17, 4}, {9, 19}, {4, 18}, {10, 10}, {12, 19}, {17, 3}, {19, 9}, {20, 16}, {11, 16}, {10, 2}, {20, 15}, {3, 14}};  // box[][0]表示宽度，box[][1]表示高度
        for (int i = 0; i < box.length; i++) {
            for (int j = 100; j > 0; j--) {
                if (box[i][1] <= 20 && box[i][0] <= j)
                    re[j] = Math.max(re[j], re[j - box[i][0]] + 1);
            }
        }
        System.out.println(re[100]);
    }
    //  answer:18
}
