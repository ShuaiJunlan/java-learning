package cn.shuaijunlan.alibaba1;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * //评测题目: 网络藏书馆中有1000亿本英文书，每本书都有英文名字和出版日期，我想要计算出每个英文单词总共出现的次数。需要关注以下几个功能
 * 1. 以多线程方式来处理这个功能
 * 2. 在处理过程中我随时需要知道处理的进度和当前的结果
 * 3. 防止并发问题
 */
public class Solution {

    /**
     * 缓冲buffer的数量，参数可根据实际内存进行调节
     */
    private static final int BUFFER_COUNT = 1 << 2;

    /**
     * 每个缓冲区的大小，参数可根据实际内存进行调节
     */
    private static final int SIZE_PER_BUFFER = 1 << 10;

    /**
     * 创建缓冲区
     */
    private static final String[][] BUFFER = new String[BUFFER_COUNT][SIZE_PER_BUFFER];


    /**
     * 核心线程数，默认为处理器的核数量
     */
    private static int corePoolSize = Runtime.getRuntime().availableProcessors();

    /**
     * 消费者线程池
     */
    private static final ThreadPoolExecutor CONSUMER_THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(corePoolSize,
            corePoolSize, 0, TimeUnit.MICROSECONDS, new SynchronousQueue<>(), new ThreadFactory() {

        private final String PREFIX_NAME = "CONSUMER_THREAD_";
        private int count = 0;
        @Override
        public Thread newThread(Runnable r) {
            if (r == null){
                throw new NullPointerException("task couldn't be null");
            }
            Thread thread = new Thread(r);
            thread.setName(PREFIX_NAME + count);
            count++;
            return thread;
        }
    });

    /**
     * 生产者线程池
     */
    private static final ThreadPoolExecutor PROVIDER_THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(BUFFER_COUNT,
            BUFFER_COUNT, 0, TimeUnit.MICROSECONDS, new SynchronousQueue<>(), new ThreadFactory() {

        private final String PREFIX_NAME = "PROVIDER_THREAD_";
        private int count = 0;
        @Override
        public Thread newThread(Runnable r) {
            if (r == null){
                throw new NullPointerException("task couldn't be null");
            }
            Thread thread = new Thread(r);
            thread.setName(PREFIX_NAME + count);
            count++;
            return thread;
        }
    });

    private static Lock lock = new ReentrantLock();
    private static Condition full = lock.newCondition();
    private static Condition empty = lock.newCondition();

    /**
     * 标记生产者是否生产完成所有的数据
     */
    private static volatile boolean finished = false;

    /**
     * 创建字典树
     */
    private static Trie trie = new Trie();
    /**
     * 存储所有单词统计数据
     */
    private static Map<String, Long> statistics = new ConcurrentHashMap<>(128);



    /////////////////////////////////核心API/////////////////////////////////
    /**
     * 整个流程的启动入口
     */
    public static void startUp() {
        startProvider();
        startConsumer();
    }

    /**
     * 获取已经处理的书名总数
     * @return 已经处理的数量
     */
    public static long getHandledAmount(){
        return CONSUME_STEPS.get() * STEP_SIZE;
    }

    /**
     * 查询某单词的出现的次数
     */
    public static long getWordCount(String word){
        if (word == null || word.trim().length() == 0){
            return 0;
        }
        return trie.getWordCount(word);
    }

    /**
     * 获取当前单词的统计情况
     * @return
     */
    public static Map getCurrentWordsCount(){
        return trie.getAllWordStatistics();
    }

    /////////////////////////////////核心API-end/////////////////////////////////


    /**
     * 标记是否有生产者线程在等待
     */
    private volatile static boolean hasProviderWaited = false;

    /**
     * 标记是否有消费者线程在等待
     */
    private volatile static boolean hasConsumerWaited = false;

    /**
     * 记录当前所有消费者线程已经消费的步数
     */
    private static final AtomicLong CONSUME_STEPS = new AtomicLong(0);

    /**
     * 每个消费的步长
     */
    private static final int STEP_SIZE = SIZE_PER_BUFFER >> 5;

    /**
     * 生产buffer的计数器
     */
    private static AtomicLong PRODUCE_BUFFER_COUNT = new AtomicLong(0);

    /**
     * 启动消费者
     */
    public static void startConsumer(){
        for (int i = 0; i < corePoolSize; i++){

            Runnable task = new Runnable() {
                //当前要消费数据的起始指针
                private long start = 0;

                //当前要消费数据的终点指针
                private long end = 0;

                //标记当前线程是否已经获得步数，-1表示没有，其他任意正整数表示已经获取过
                private long currentStep = -1;

                @Override
                public void run() {
                    while (true){
                        //防止等待被唤醒后重复消费
                        if (currentStep == -1){
                            currentStep = CONSUME_STEPS.getAndIncrement();
                        }

                        start = currentStep * STEP_SIZE;
                        //
                        if (start < PRODUCE_BUFFER_COUNT.get() * SIZE_PER_BUFFER){

                            //被消费的buffer[]的index
                            int index = (int)(start % SIZE_PER_BUFFER);

                            //算出起始位置
                            start %= SIZE_PER_BUFFER;

                            //算出终点的位置
                            end = start + STEP_SIZE;
                            end %= SIZE_PER_BUFFER;

                            //构建字典树
                            for (int i = (int) start; i < end; i++){
                                //插入书名
                                addString(
                                        BUFFER[index][i]
                                );
                            }

                            currentStep = -1;

                            //如果有生产者等待，且有一个完整的空闲buffer，则通知消费者
                            if (hasProviderWaited && end == SIZE_PER_BUFFER){
                                lock.lock();
                                try {
                                    full.notifyAll();
                                }finally {
                                    hasProviderWaited = false;
                                    lock.unlock();
                                }
                            }

                        }else { //如消费的数量达到了生产的数量，则让当前线程等待

                            if (finished){ //消费完所有数据，则退出
                                return;
                            }
                            lock.lock();
                            try {
                                hasConsumerWaited = true;
                                empty.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }finally {
                                lock.unlock();
                            }
                        }
                    }
                }
            };
            CONSUMER_THREAD_POOL_EXECUTOR.execute(task);
        }
    }



    /**
     * 启动生产者
     */
    public static void startProvider(){
        for (int i = 0; i < BUFFER_COUNT ; i++) {
            Runnable task = new Runnable() {
                private long index = -1;

                @Override
                public void run() {
                    while (true) {
                        if (index == -1){
                            //生产者生产数据索引
                            index = PRODUCE_BUFFER_COUNT.getAndIncrement();
                        }

                        long count = CONSUME_STEPS.get() * STEP_SIZE;

                        if (index - count / BUFFER_COUNT >= BUFFER_COUNT){
                            lock.lock();
                            try {
                                hasProviderWaited = true;
                                full.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }finally {
                                lock.unlock();
                            }
                        }else {

                            String[] data = getDataFromAnyway();
                            //如果全部数据处理完了，则退出
                            if (data == null || data.length == 0){
                                //标记数据已全部生产完成
                                finished = true;
                                return;
                            }
                            BUFFER[(int)(index % BUFFER_COUNT)] = data;

                            index = -1;

                            if (hasConsumerWaited){
                                lock.lock();
                                try {
                                    hasConsumerWaited = false;
                                    empty.notifyAll();
                                }finally {
                                    lock.unlock();
                                }
                            }

                        }
                    }
                }
            };
            PROVIDER_THREAD_POOL_EXECUTOR.execute(task);
        }
    }



    /**
     * 插入字符串
     * @param str 书名
     */
    private static void addString(String str){
        if (str == null || str.trim().length() == 0){
            return;
        }
        String[] strings = str.split(" ");
        for (String s : strings){
            trie.addWord(s);
        }
    }

    /**
     * 获取数据
     * @return data
     */
    private static String[] getDataFromAnyway(){
        return new String[SIZE_PER_BUFFER];
    }
}