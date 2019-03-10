package cn.shuaijunlan.java.basic.learning.waitandsleep;

import org.junit.Test;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 10:23 AM 11/16/18.
 */
public class WaitTest {
    private Object object = new Object();
    @Test
    public void test1(){
        synchronized (object){
            System.out.println("hello");
            try {
                object.wait(1000);
                System.out.println("world");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test2(){
        System.out.println("hello");
        try {
            //java.lang.IllegalMonitorStateException
            object.wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("world");
    }

    @Test
    public void test3(){
        synchronized (object){
            System.out.println("hello");
            try {
                // object.wait();
                object.wait(0);
                System.out.println("world");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
