package cn.shuaijunlan.lanqiao.Test;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 17:30 2017/3/22.
 */
public class App10 {
    public static void main(String[] args) {
        for (int i = 10; i <= 99; i++) {
            int temp = i % 10;
            int temp1 = i / 10;
            if (i - (temp * 10 + temp1) == 27)
                System.out.println(i);
        }
    }
}
