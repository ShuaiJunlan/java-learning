package cn.shuaijunlan.java.basic.learning.concurrent.blockingqueue;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Junlan Shuaijunlan[shuaijunlan@gmail.com].
 * @since Created in 12:01 PM 10/16/18.
 */
public class BlockingQueueTest {
    /**
     * (Integer.MAX_VALUE+2) < 0
     */
    @Test
    public void test1(){
        LinkedBlockingQueue<Long> linkedBlockingQueue = new LinkedBlockingQueue();
        for (long i = 0; i < (Integer.MAX_VALUE+2); i++){
            linkedBlockingQueue.add(i);
        }
        System.out.println(linkedBlockingQueue.size());
    }

    @Test
    public void test2(){
        LinkedBlockingQueue<Long> linkedBlockingQueue = new LinkedBlockingQueue();
        for (long i = 0; i < Integer.MAX_VALUE; i++){
            linkedBlockingQueue.add(i);
        }
        System.out.println(linkedBlockingQueue.size());
    }
}
