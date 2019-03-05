package cn.shuaijunlan.java.basic.learning.completablefuture;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 9:45 AM 2018/09/07.
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        // CompletableFuture
    }
    @Test
    public void test1() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i = 1/1;
            return 100;
        });
        //future.join();
        System.out.println(future.get());
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> f = compute();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    f.complete(1);
                }
            }
        }).start();
        System.out.println(f.get());
    }
    @Test
    public void test3() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> f = compute();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (long i = 0; i < Long.MAX_VALUE ; i++){
                        // Thread.sleep(1);

                    }
                } finally {
                    f.complete(1);
                }
            }
        }).start();
        System.out.println(f.get());
    }
    public static CompletableFuture<Integer> compute() {
        final CompletableFuture<Integer> future = new CompletableFuture<>();
        return future;
    }
}
