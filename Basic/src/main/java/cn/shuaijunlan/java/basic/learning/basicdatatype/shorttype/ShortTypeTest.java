package cn.shuaijunlan.java.basic.learning.basicdatatype.shorttype;

import org.junit.Test;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 2:50 PM 12/11/19.
 */
public class ShortTypeTest {
    /**
     * Reference: https://blog.csdn.net/youngyouth/article/details/79854483
     */
    @Test
    public void test1() {
        short s1 = 1, s2 = 1, s3 = 1;
        //Required type:
        // short
        // Provided:
        // int
        //s1 = s1 + 1;
        s2 += 1;
        s3 ++;
        ++s3;
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}
