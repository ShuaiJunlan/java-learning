package cn.shuaijunlan.lanqiao.Test;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 17:32 2017/3/22.
 */
public class App9 {
    public static void main(String[] args) {
        for (int i = 0; true; i++) {
            int j = i;
            int sum = 0;
            while (sum <= 236) {
                if (sum == 236)
                    System.out.println(i);
                else
                    sum += (j++);
            }
        }
    }
}
