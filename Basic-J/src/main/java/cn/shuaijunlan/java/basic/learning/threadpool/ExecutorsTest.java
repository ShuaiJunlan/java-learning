package cn.shuaijunlan.java.basic.learning.threadpool;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author Junlan Shuaijunlan[shuaijunlan@gmail.com].
 * @since Created in 4:39 PM 10/11/18.
 */
public class ExecutorsTest {
    @Test
    public void test1(){
        Executor executor = Executors.newSingleThreadExecutor();
        Executor executor1 = Executors.newFixedThreadPool(3);
        Executor executor2 = Executors.newCachedThreadPool();
        ThreadPoolExecutor executor3 = new ThreadPoolExecutor(1, 3, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());
    }
}
