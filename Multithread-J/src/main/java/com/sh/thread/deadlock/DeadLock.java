package com.sh.thread.deadlock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 10:36 2018/4/14.
 */
public class DeadLock {
    static class Friend{
        private final String name;
        public Friend(String name){
            this.name = name;
        }

        public String getName(){
            return this.name;
        }

        public synchronized void bow(Friend friend){
            System.out.format("%s:%s" + " has bowed to me!%n", this.name, friend.getName() + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            friend.bowBack(this);
        }
        public synchronized void bowBack(Friend friend){

            System.out.format("%s:%s" + " has bowed back to me!%n", this.name, friend.getName() + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Friend friendA = new Friend("Shuai");
        final Friend friendB = new Friend("Junlan");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                2, 1000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(2));
        /// Why not using this way to create ThreadPool?
        // ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        threadPoolExecutor.execute(() -> friendA.bow(friendB));
        threadPoolExecutor.execute(() -> friendA.bowBack(friendB));
        threadPoolExecutor.shutdown();

    }
}
