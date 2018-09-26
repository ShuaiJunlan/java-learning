package cn.shuaijunlan.java.basic.learning.integercache;

import org.junit.Test;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 4:30 PM 2018/09/19.
 */
public class IntegerCacheTest {
    @Test
    public void test1(){
        Integer a = 100, b = 100;
        System.out.println(a == b);
        Integer c = 300, d = 300;
        System.out.println(c == d);
    }
    @Test
    public void test2(){
        Integer a = new Integer(1);
        Integer b = new Integer(1);
        System.out.println(a == b);
    }
}
