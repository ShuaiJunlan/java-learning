package cn.shuaijunlan.java.basic.learning.completablefuture;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 3:06 PM 2018/09/07.
 */
public class FutureDemo {
    /**
     * Testing Callable & Future
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test1() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Task task = new Task();
        Future<Integer> future = executorService.submit(task);
        executorService.shutdown();
        System.out.println("Getting result from executing result: " + future.get());

    }

    /**
     * Testing Callable & FutureTask
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test2() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        FutureTask<Integer> futureTask = new FutureTask<>(new Task());
        executorService.submit(futureTask);
        System.out.println("Getting result from executing task: " + futureTask.get());

    }

    /**
     * Testing Thread & FutureTask
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test3() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new Task());
        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println("Getting result from executing task: " + futureTask.get());
    }
}

class Task implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("Task is running!");
        Thread.sleep(100);
        int sum = 0;
        for (int i = 1; i <= 100; i++){
            sum += i;
        }
        return sum;
    }
}
