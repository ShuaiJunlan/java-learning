package cn.shuaijunlan.lanqiao.$2015.XB_JAVA_A;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 19:36 2017/3/12.
 */
public class $03 {
    public static void main(String[] args) {
        double a = 0.0000001;
        double b = 2.5;
//        System.out.println(Math.pow(2.5,2.5));
        while (Math.pow(a + b, a + b) <= 10.0) {
            b = a + b;
        }
        System.out.println(b);

        System.out.println(Math.pow(2.506184, 2.506184));
        System.out.println(Math.pow(2.506185, 2.506185));
    }
}
