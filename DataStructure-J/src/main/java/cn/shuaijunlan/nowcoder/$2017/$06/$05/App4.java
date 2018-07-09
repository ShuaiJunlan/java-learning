package cn.shuaijunlan.nowcoder.$2017.$06.$05;

import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 20:02 2017/6/5.
 */
public class App4 {
    public static int n;
    public static int m;
    public static int[] heights;
    public static long count = 0;
    public static long MOD = 1000000007;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        heights = new int[n];
        for (int i = 0; i < n; i++){
            heights[i] = scanner.nextInt();
        }
        bfs(0);
        System.out.println(count);
    }
    public static void bfs(int step){
        if (step == n - 1){
            if (getNum() == m) {
                count++;
                count %= MOD;
            }
            return;
        }
        for (int i = step; i < n; i++){
            swap(step, i);
            bfs(step+1);
            swap(step, i);
        }
    }
    public static void swap(int i, int j){
        int temp = heights[i];
        heights[i] = heights[j];
        heights[j] = temp;
    }
    public static int getNum(){
        int count = 0;//记录将军看到的士兵的个数
        long max = -1;
        for (int j = 0; j < n; j++) {
            if (heights[j] > max) {
                count++;
                max = heights[j];
            }
        }
        return count;
    }
}