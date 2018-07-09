package cn.shuaijunlan.lanqiao.$2015.CH_JAVA_A;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 21:44 2017/3/2.
 */
public class Test1 {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (i * i + i + 2 * i * j + 2 * j == 198)
                    System.out.println(i + " " + j);
            }
        }
    }
}
