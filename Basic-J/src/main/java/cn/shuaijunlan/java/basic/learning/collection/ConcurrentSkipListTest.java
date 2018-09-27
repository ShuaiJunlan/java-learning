package cn.shuaijunlan.java.basic.learning.collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 9:38 AM 2018/09/27.
 */
public class ConcurrentSkipListTest {
    @Test
    public void test1(){
        // ConcurrentSkipList  concurrentSkipList = new Co
        ConcurrentSkipListSet<Integer> concurrentSkipListSet = new ConcurrentSkipListSet<>();
        Random random = new Random();
        for (int i = 0; i <= 10000000; i++){
            concurrentSkipListSet.add(i + random.nextInt(100000));
        }
        Long start = System.currentTimeMillis();
        Iterator<Integer> integers = concurrentSkipListSet.subSet(1000,100000000).iterator();
        Long end = System.currentTimeMillis();
        while (integers.hasNext()){
            System.out.println(integers.next());
        }
        System.out.println("Time use:" + (end - start));

    }
}
