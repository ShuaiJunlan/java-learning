package cn.shuaijunlan.java.basic.learning.concurrent.blockingqueue;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 8:04 PM 2018/10/02.
 */
@Slf4j
public class BlockQueueTest {

    @Test
    public void test2() throws InterruptedException {
        CustomBlockQueue<Integer> blockQueueWithCondition = new CustomBlockQueue<>(1);
        List<Integer> list = new ArrayList();

        Thread thread1 = new Thread(() -> {
            try {
                while (true){
                    if (log.isInfoEnabled()){
                        log.info(blockQueueWithCondition.take().toString());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                while (true){
                    if (log.isInfoEnabled()){
                        log.info(blockQueueWithCondition.take().toString());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 1000; i++){
                try {
                    list.add(i);
                    blockQueueWithCondition.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 1000; i++){
                try {
                    list.add(i);
                    blockQueueWithCondition.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread5 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++){
                    list.add(i);

            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
    }
}
