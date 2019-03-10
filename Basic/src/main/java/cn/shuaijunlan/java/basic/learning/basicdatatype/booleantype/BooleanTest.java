package cn.shuaijunlan.java.basic.learning.basicdatatype.booleantype;

import org.junit.Test;

/**
 * @author Junlan Shuaijunlan[shuaijunlan@gmail.com].
 * @since Created in 6:27 PM 10/12/18.
 */
public class BooleanTest {
    @Test
    public void test1(){
        Boolean b1 = new Boolean(true);
        Boolean b2 = new Boolean(true);
        System.out.println(b1 == b2);
    }

    @Test
    public void test2(){
        Boolean b1 = true;
        Boolean b2 = true;
        System.out.println(b1 == b2);
    }
}
