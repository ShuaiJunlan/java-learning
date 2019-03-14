package cn.shuaijunlan.alibaba;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
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
     * 数据缓冲
     */
    static class Buffer {
         String[] buffer = new String[SIZE_PER_BUFFER];
    }
    /**
     * 缓冲buffer的数量
     */
    private static final int BUFFER_COUNT = 1 << 2;
    /**
     * 每个缓冲区的大小
     */
    private static final int SIZE_PER_BUFFER = 1 << 10;
    /**
     * 创建缓冲区
     */
    private static final Buffer[] BUFFER = new Buffer[BUFFER_COUNT];

    static {
        for (int i = 0; i < BUFFER_COUNT; i++){
            BUFFER[i] = new Buffer();
        }
    }

    /**
     * 生产buffer的计数器
     */
    private static AtomicLong PROVIDER_BUFFER_COUNT = new AtomicLong(0);

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
    private static WordDictionary wordDictionary = new WordDictionary();

    /**
     * 整个流程的启动入口
     */
    public static void startUp() {
        startProvider();
        startConsumer();
    }

    /**
     * 记录当前所有消费者线程已经消费的步数
     */
    private static final AtomicInteger STEPS = new AtomicInteger(0);

    /**
     * 每个消费的步长
     */
    private static final int STEP_SIZE = SIZE_PER_BUFFER >> 5;

    /**
     *
     * 标记是否有生产者线程在等待
     */
    private volatile static boolean hasProviderWaited = false;

    /**
     *
     * 标记是否有消费者线程在等待
     */
    private volatile static boolean hasConsumerWaited = false;

    /**
     * 启动消费者
     */
    public static void startConsumer(){
        for (int i = 0; i < corePoolSize; i++){

            Runnable task = new Runnable() {
                //当前要消费数据的起始指针
                private int start = 0;

                //当前要消费数据的终点指针
                private int end = 0;

                private int currentStep = -1;
                @Override
                public void run() {
                    while (true){
                        if (currentStep == -1){
                            currentStep = STEPS.getAndIncrement();
                        }

                        start = currentStep * STEP_SIZE;
                        //
                        if (start < PROVIDER_BUFFER_COUNT.get() * SIZE_PER_BUFFER){

                            //算出起始位置
                            start %= SIZE_PER_BUFFER;

                            //算出终点的位置
                            end = start + STEP_SIZE;
                            end %= SIZE_PER_BUFFER;

                            //被消费的buffer的index
                            int index = (int)(PROVIDER_BUFFER_COUNT.get()%BUFFER_COUNT);
                            //构建字典树
                            for (int i = start; i < end; i++){
                                //插入书名
                                wordDictionary.addString(
                                        BUFFER[index].buffer[i]
                                );
                            }

                            currentStep = -1;

                            //通知生产者
                            if (hasProviderWaited){
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
                            index = PROVIDER_BUFFER_COUNT.getAndIncrement();
                        }

                        long count = (long)STEPS.get() * STEP_SIZE;

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
                            BUFFER[(int) (index % BUFFER_COUNT)].buffer = data;

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
     * 获取数据
     * @return
     */
    public static String[] getDataFromAnyway(){
        return new String[SIZE_PER_BUFFER];
    }

    /**
     * 获取当前的状态
     * @return
     */
    public static State getCurrentState(){
        return new State();
    }
}


class WordDictionary {
    /**
     * A-Z : 65-90
     * a-z : 97-122
     *
     * 字典树的节点
     */
    private class TrieNode{
        TrieNode[] children = new TrieNode[52];
        /**
         * 标记当前的单词
         */
        String item = null;
        /**
         * 记录当前单词出现的次数
         */
        public AtomicLong count = new AtomicLong(0);
    }

    /**
     * 字典树的根节点
     */
    private TrieNode root = new TrieNode();

    public WordDictionary() {

    }

    /**
     * 插入字符串
     * @param str
     */
    public void addString(String str){
        if (str == null || str.trim().length() == 0){
            return;
        }
        String[] strings = str.split(" ");

        for (String s : strings){
            addWord(s);
        }
    }

    private final Object LOCK = new Object();

    /**
     * 插入单词
     * @param word
     */
    private void addWord(String word) {
        if (word == null || word.length() == 0){
            return;
        }
        TrieNode node = root;
        for (char c : word.toCharArray()){
            //算出对应的TrieNode的index
            int index = c > 90 ? (c-'a' + 26) : (c-'A');

            if (node.children[index] == null){
                synchronized (LOCK){
                    if (node.children[index] == null){
                        node.children[index] = new TrieNode();
                    }
                }
            }
            node = node.children[index];
        }
        node.item = word;
        node.count.getAndIncrement();
    }

    /**
     * 获取单词出现的次数
     * @param word
     * @return
     */

    public long getWordCount(String word){
        if (word == null || word.length() == 0){
            return 0;
        }
        return match(word.toCharArray(), root);

    }
    private long match(char[] chs, TrieNode node){
        int i = 0;
        while (i < chs.length){
            char c = chs[i];
            if (c > 90){
                node = node.children[c-'a'+26];
            }else {
                node = node.children[c-'A'];
            }
            if (node == null){
                return 0;
            }
            i++;
        }
        return node.count.get();
    }
}
class State{

}