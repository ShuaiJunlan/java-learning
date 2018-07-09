package cn.shuaijunlan.lanqiao.simu5;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 15:24 2017/4/1.
 */
public class App1 {
    public static void main(String[] args) {
        for (int i = 10; i <= 99; i++) {
            int j = (i / 10 + (i % 10) * 10);
            int x = j + i;
            int y = Math.abs(j - i);
            if (x - y == 32)
                System.out.println(y);
        }
        // answer：45
    }
}
