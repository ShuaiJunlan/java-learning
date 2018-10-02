package cn.shuaijunlan.java.basic.learning.reentrantlock;

import org.junit.Test;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 8:04 PM 2018/10/02.
 */
public class BlockQueueTest {
    @Test
    public void test1() throws InterruptedException {
        BlockQueueWithSignal<Integer> blockQueueWithSignal = new BlockQueueWithSignal<>();
        new Thread(() -> {
            for (int i = 0; i < 17; i++){
                try {
                    blockQueueWithSignal.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            try {
                while (true){
                    System.out.println(blockQueueWithSignal.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(1000);


    }
}
