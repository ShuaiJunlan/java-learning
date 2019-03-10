package cn.shuaijunlan.java.basic.learning.trycatch;

import org.junit.Test;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 10:18 AM 11/24/18.
 */
public class TryReturnTest {
    @Test
    public void test1(){
        System.out.println(getBoolean());
    }

    boolean getBoolean(){
        try {
            // for (;;){
                int i = 1 / 0;
                return true;
            // }
        }finally {
            System.out.println("finally");
        }
    }
}
