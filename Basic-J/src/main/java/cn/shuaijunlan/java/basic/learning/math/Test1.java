package cn.shuaijunlan.java.basic.learning.math;

import org.junit.Test;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 1:53 PM 1/11/19.
 */
public class Test1 {
    public static void main(String[] args) {
        int x = -5;
        int y = -12;
        System.out.println(y % x);
    }
    @Test
    public void test1(){
        float t = 5.1f;
        int i = (byte)t;
    }
    @Test
    public void test2(){
        int a = 1;
        System.out.println(a++ + ++a);
    }
}
