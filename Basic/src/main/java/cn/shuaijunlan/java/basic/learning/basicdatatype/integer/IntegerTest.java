package cn.shuaijunlan.java.basic.learning.basicdatatype.integer;

import org.junit.Test;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 2:22 PM 3/3/19.
 */
public class IntegerTest {
    public static void main(String[] args) {
        System.out.println(Integer.toHexString(19961022));
    }

    @Test
    public void test1(){
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;

        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a+b));
        System.out.println(c.equals(a+b));
        System.out.println(g == (a+b));
        System.out.println(g.equals(a+b));
    }

    @Test
    public void test2(){
        System.out.println(Integer.MAX_VALUE + Integer.MAX_VALUE);
    }
}
