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

    /**
     * If the task queue is empty, is there any active threads in the thread pool?
     * Because of creating thread poll with {@link Executors#newFixedThreadPool(int)},
     * so when there is not task, if the number of threads is less than corePoolSize,
     * all threads also be active, the part that exceeds the core sizes will be terminated after keepAliveSize time.
     */
    @Test
    public void test2() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> System.out.println("Hello"));
        // executor.execute(() -> System.out.println("Hello"));
        executor.awaitTermination(1000, TimeUnit.SECONDS);

    }

    /**
     * If the task queue is empty, is there any active threads in the thread pool?
     * Because of creating thread pool with {@link Executors#newCachedThreadPool()}, so when there is not task, </br>
     * all threads will be terminated after 60s by default.
     * @see ThreadPoolExecutor#getTask()
     */
    @Test
    public void test3() throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(() -> System.out.println("Hello"));
        executor.awaitTermination(1000, TimeUnit.SECONDS);

    }
}
