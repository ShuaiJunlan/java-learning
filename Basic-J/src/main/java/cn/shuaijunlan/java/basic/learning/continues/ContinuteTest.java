package cn.shuaijunlan.java.basic.learning.continues;

import org.junit.Test;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 4:09 PM 11/28/18.
 */
public class ContinuteTest {
    @Test
    public void test1(){
        int i = 0;

        while (i==0){
            try {
                if (i == 0){
                    i--;

                    continue;
                }
                System.out.println("jjj");
            }finally {
                System.out.println("hhh");
            }
        }

    }
}
