package cn.shuaijunlan.java.basic.learning.trycatch;

import org.junit.Test;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 3:31 PM 2018/09/17.
 */
public class TryCatchTest {

    /**
     * Testing return and System.exit()
     */
    @Test
    public void test1(){
        // try {
        //     System.out.println(1);
        //     return;
        // }finally {
        //     System.out.println(2);
        // }

        try {
            System.out.println(1);
            System.exit(1);
        }finally {
            System.out.println(2);
        }

    }

    /**
     * Testing try-catch returning value
     * If there is a return statement in finally block, it will execute,
     * If there isn't a return statement in finally block, and there isn't exception in try block, the value will be returned in try block,
     * others, the value will be returned in catch block.
     */
    @Test
    public void test(){
        System.out.println(getVaule());
    }

    public int getVaule(){
        try {
            int b = 1 / 0;
            return 1;
        }catch (Exception e){
            return 2;
        }finally {
            return 3;
        }
    }
}
