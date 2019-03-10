package cn.shuaijunlan.java.basic.learning.multithread.thread;

import org.junit.Test;

/**
 * @author Junlan Shuaijunlan[shuaijunlan@gmail.com].
 * @since Created in 9:25 PM 10/12/18.
 */
public class ThreadTest {
    @Test
    public void test1(){
        Thread thread = new Thread();
    }

    @Test
    public void test2() throws InterruptedException {
        Thread.sleep(0);
        System.out.println("what is the meaning");
    }
}
