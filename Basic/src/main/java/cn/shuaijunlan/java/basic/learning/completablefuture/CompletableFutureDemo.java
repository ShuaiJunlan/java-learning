package cn.shuaijunlan.java.basic.learning.completablefuture;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;

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
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    f.complete(1);
                }
            }
        }).start();

        f.whenComplete(new BiConsumer<Integer, Throwable>() {
            @Override
            public void accept(Integer integer, Throwable throwable) {
                if (throwable == null){
                    System.out.println(integer);
                }else {
                    throwable.getMessage();
                }
            }
        });
        System.out.println("hhh");
        Thread.sleep(6000);
        // System.out.println(f.get());
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

    @Test
    public void test4() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> f = compute();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }finally {
                            f.complete(1);
                        }
                    }
                }).start();

                f.whenComplete(new BiConsumer<Integer, Throwable>() {
                    @Override
                    public void accept(Integer integer, Throwable throwable) {
                        if (throwable == null){
                            System.out.println(integer);
                        }else {
                            throwable.getMessage();
                        }
                    }
                });
            }
        });
        thread.start();

        Thread.sleep(3000);

        thread.interrupt();
        // thread.destroy();

        Thread.sleep(10000);
        // System.out.println(f.get());
    }

    private static CompletableFuture<Integer> compute() {
        final CompletableFuture<Integer> future = new CompletableFuture<>();
        return future;
    }
}
