package com.sh.thread.ProduceAndConsumeModel;

import java.util.concurrent.*;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 16:23 2017/4/12.
 */
public class BlockingQueueTest {
    public class Basket {
        BlockingQueue<String> basket = new LinkedBlockingQueue<String>(3);

        public void produce() throws InterruptedException {
            try {
                basket.put("An apple");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public String consume() throws InterruptedException {
            try {
                return basket.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    class Producer implements Runnable {
        private String instance;
        private Basket basket;

        public Producer(String instance, Basket basket) {
            this.instance = instance;
            this.basket = basket;
        }

        public void run() {
            try {
                while (true) {
                    System.out.println("生产者" + instance + "开始");
                    basket.produce();
                    System.out.println("生产者" + instance + "结束");

                    Thread.sleep(400);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Consumer implements Runnable {
        private String instance;
        private Basket basket;

        public Consumer(String instance, Basket basket) {
            this.instance = instance;
            this.basket = basket;
        }

        public void run() {
            try {
                while (true) {
                    System.out.println("消费者：" + instance + "开始");
                    System.out.println(basket.consume());
                    System.out.println("消费者：" + instance + "结束");

                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        BlockingQueueTest blockingQueueTest = new BlockingQueueTest();
        Basket basket = blockingQueueTest.new Basket();

        Producer producer1 = blockingQueueTest.new Producer("pro1", basket);
        Producer producer2 = blockingQueueTest.new Producer("pro2", basket);
        Consumer consumer = blockingQueueTest.new Consumer("con1", basket);
        es.submit(producer1);
        es.submit(producer2);
        es.submit(consumer);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        es.shutdownNow();

    }
}
