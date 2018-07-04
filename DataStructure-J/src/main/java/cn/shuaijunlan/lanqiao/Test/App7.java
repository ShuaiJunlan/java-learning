package cn.shuaijunlan.lanqiao.Test;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 19:01 2017/3/22.
 */
public class App7 {
    public static void main(String[] args) {
        int j = 1;
        int sum = 1;
        for (int i = 2; i <= 100; i++) {
            j = j + i;
            sum += (j);
        }
        System.out.println(sum);
    }
}
