package cn.shuaijunlan.java.basic.learning.threadpool;

import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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
    }
}
