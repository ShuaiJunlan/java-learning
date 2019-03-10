package cn.shuaijunlan.java.basic.learning.basicdatatype.integer;

import org.junit.Test;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 4:27 PM 2018/09/19.
 */
public class LongCacheTest {
    @Test
    public void test1(){
        Long a = 100L, b = 100L;
        System.out.println(a == b);
        Long c = 300L, d = 300L;
        System.out.println(c == d);
    }
}
