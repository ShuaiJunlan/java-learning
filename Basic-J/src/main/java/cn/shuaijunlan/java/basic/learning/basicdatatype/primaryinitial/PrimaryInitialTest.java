package cn.shuaijunlan.java.basic.learning.basicdatatype.primaryinitial;

import org.junit.Test;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 8:31 PM 1/7/19.
 * TODO: Why?
 */
public class PrimaryInitialTest {
    @Test
    public void test1(){
        int i;
        // int j = i + 1; //Error:(13, 17) java: variable i might not have been initialized
        // System.out.println(j);
    }
    int k;
    @Test
    public void test2(){
        int j = k + 1;
        System.out.println(j);
    }
}
