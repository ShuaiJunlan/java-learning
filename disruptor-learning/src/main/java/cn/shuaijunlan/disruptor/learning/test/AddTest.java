package cn.shuaijunlan.disruptor.learning.test;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:19 PM 5/21/19.
 */
public class AddTest {
    private static long MAX_VALUE = 500_000_000L;
    private long i = 0;
    private volatile long j = 0;
    private LongAdder longAdder = new LongAdder();

    private AtomicLong atomicLong = new AtomicLong(0);
    @Test
    public void test1() {
        long start = System.currentTimeMillis();
        while (i < MAX_VALUE){
            i++;
        }
        System.out.println(System.currentTimeMillis()-start);
    }
    @Test
    public void test2() {
        long start = System.currentTimeMillis();
        while (j < MAX_VALUE){
            j++;
        }
        System.out.println(System.currentTimeMillis()-start);
    }
    @Test
    public void test3() {
        i = 0 ;
        long start = System.currentTimeMillis();
        while (i < MAX_VALUE){
            synchronized (this){
                i++;
            }
        }
        System.out.println(System.currentTimeMillis()-start);
    }
    @Test
    public void test4() {
        long start = System.currentTimeMillis();
        while (longAdder.longValue() < MAX_VALUE){
            longAdder.increment();
        }
        System.out.println(System.currentTimeMillis()-start);
    }

    @Test
    public void test5() {
        long start = System.currentTimeMillis();
        while (atomicLong.getAndIncrement() < MAX_VALUE){
            // atomicLong.incrementAndGet();
        }
        System.out.println(System.currentTimeMillis()-start);
    }

    // 293      single thread
    // 3473     single thread with volatile
    // 9161     single thread with synchronized
    // 4335     single thread with LongAdder
    // 3387     single thread with AtomicLong
}
