package cn.shuaijunlan.java.basic.learning.doubletest;

import org.junit.Test;

/**
 * @author Junlan Shuaijunlan[shuaijunlan@gmail.com].
 * @since Created in 10:47 AM 10/10/18.
 */
public class DoubleTest {
    @Test
    public void test1(){
        double a = 0.22, b = 0.22;
        System.out.println(a == b);
        double c = 1.01;
        double d = 1.01;
        System.out.println(d == c);

    }

    /**
     * return true.
     */
    @Test
    public void test2(){
        double c = 0.1000000000000000000100000000000001;
        double d = 0.1000000000000000000100000000000002;
        System.out.println(d == c);
    }

    /**
     * return false, why?
     */
    @Test
    public void test3(){
        double c = 0.0000000000000000000100000000000001;
        double d = 0.0000000000000000000100000000000002;
        System.out.println(d == c);
    }
}
