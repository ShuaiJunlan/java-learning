package cn.shuaijunlan.java.basic.learning.exception;

import org.junit.Test;

/**
 * @author Junlan Shuaijunlan[shuaijunlan@gmail.com].
 * @since Created in 10:25 AM 10/12/18.
 */
public class ExceptionTest {
    public void getValue(){
        throw  new NullPointerException();
    }

    public void getValue1() throws NullPointerException{
        throw  new NullPointerException();
    }

    @Test
    public void test1(){
        getValue();
    }

    @Test
    public void test2(){
        try {
            getValue();
        }catch (NullPointerException e){
            e.printStackTrace();
        }finally {
            System.out.println("Finish");
        }
    }
}
