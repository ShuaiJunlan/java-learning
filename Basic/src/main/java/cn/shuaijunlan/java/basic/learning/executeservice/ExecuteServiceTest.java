package cn.shuaijunlan.java.basic.learning.executeservice;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 8:07 PM 2018/09/19.
 */
public class ExecuteServiceTest {
    /**
     * Testing difference between execute() and submit()
     */
    @Test
    public void test1(){
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> System.out.println("Hello"));
        Future<String> stringFuture = executorService.submit(() -> "Hello");
        try {
            System.out.println(stringFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello Runnable");
            }
        };
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        };
    }
    @Test
    public void test2(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1));
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(20);
                    System.out.println(11);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        for (int i = 0; i < 10; i++){
            executor.execute(thread);
        }

        try {
            TimeUnit.SECONDS.sleep(233);
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
