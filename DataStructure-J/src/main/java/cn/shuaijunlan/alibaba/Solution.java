package cn.shuaijunlan.alibaba;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * //评测题目: 网络藏书馆中有1000亿本英文书，每本书都有英文名字和出版日期，我想要计算出每个英文单词总共出现的次数。需要关注以下几个功能
 * 1. 以多线程方式来处理这个功能
 * 2. 在处理过程中我随时需要知道处理的进度和当前的结果
 * 3. 防止并发问题
 *
 * //解题思路：
 * 1，将1000亿个书名，分块读取，每一块对应程序中的一个buffer，在程序中分配了BUFFER_COUNT个BUFFER
 * 2，基于生产者-消费者模型，当有buffer空闲时，生产者线程向buffer中读入一个块的数据
 * 3，当buffer被填充了数据时，则消费者线程进行消费，每次消费的数据块大小为STEP_SIZES
 * 4，基于{@link ConcurrentHashMap}构建（单词->出现次数）的统计信息
 * 5，提供了如下API：
 *      {@link Solution#startUp()}              整个流程的启动入口
 *      {@link Solution#getWordCount(String)}   获取某个单词出现的次数
 *      {@link Solution#getCurrentWordsCount()} 获取当前所有单词统计情况
 *      {@link Solution#getHandledAmount()}     获取已经被统计的书的总数
 *      {@link Solution#getDataFromAnyway()}    通过重写该函数，可以定义任意的数据源
 *
 */
public class Solution {

    /**
     * 缓冲buffer的数量，参数可根据实际内存进行调节
     */
    private final int BUFFER_COUNT = 1 << 2;

    /**
     * 每个缓冲区的大小，参数可根据实际内存进行调节
     */
    private final int SIZE_PER_BUFFER = 1 << 14;

    /**
     * 创建缓冲区
     */
    private final String[][] BUFFER = new String[BUFFER_COUNT][SIZE_PER_BUFFER];

    /**
     * 标记BUFFER是否加载成功
     */
    private final  AtomicBoolean[] BUFFER_FLAG = new AtomicBoolean[BUFFER_COUNT];

    /**
     * 构造函数
     */
    public Solution(){
        for (int i = 0; i < BUFFER_COUNT; i++){
            BUFFER_FLAG[i] = new AtomicBoolean(false);
        }
    }

    /**
     * 存储所有单词统计数据,key为单词，value为出现的次数
     */
    private Map<String, LongAdder> statistics = new ConcurrentHashMap<>(128);


    /////////////////////////////////核心API-start/////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////

    /**
     * 整个流程的启动入口
     * @see #startProvider()
     * @see #startConsumer()
     * @see #join()
     */
    public void startUp() {
        //启动生产者
        startProvider();

        //启动消费者
        startConsumer();

        //等待任务全部完成
        join();
    }

    /**
     * 获取已经处理的书名总数
     * @return 已经处理的数量
     */
    public long getHandledAmount(){
        return CONSUME_STEPS.get() * STEP_SIZE;
    }

    /**
     * 查询某单词的出现的次数
     * @param word 单词
     * @return 出现的次数
     */
    public long getWordCount(String word){
        if (word == null || word.trim().length() == 0){
            return 0;
        }
        return statistics.get(word).longValue();
    }

    /**
     * 获取当前单词的统计情况
     * @return map
     */
    public Map getCurrentWordsCount(){
        return statistics;
    }

    /////////////////////////////////核心API-end/////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    /**
     * 核心线程数，默认为处理器的核数
     */
    private int consumerCorePoolSize = Runtime.getRuntime().availableProcessors();

    /**
     * 消费者线程池
     */
    private final ThreadPoolExecutor CONSUMER_THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(consumerCorePoolSize,
            consumerCorePoolSize, 0, TimeUnit.MICROSECONDS, new SynchronousQueue<>(), new ThreadFactory() {

        private final String PREFIX_NAME = "CONSUMER_THREAD_";
        private int count = 0;
        @Override
        public Thread newThread(Runnable r) {
            if (r == null){
                throw new NullPointerException("task couldn't be null");
            }
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            thread.setName(PREFIX_NAME + count);
            count++;
            return thread;
        }
    });

    /**
     * 消费者线池核心线程数
     */
    private int providerCorePoolSize = 2;

    /**
     * 生产者线程池
     */
    private final ThreadPoolExecutor PROVIDER_THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(providerCorePoolSize,
            providerCorePoolSize, 0, TimeUnit.MICROSECONDS, new SynchronousQueue<>(), new ThreadFactory() {

        private final String PREFIX_NAME = "PROVIDER_THREAD_";
        private int count = 0;
        @Override
        public Thread newThread(Runnable r) {
            if (r == null){
                throw new NullPointerException("task couldn't be null");
            }
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            thread.setName(PREFIX_NAME + count);
            count++;
            return thread;
        }
    });

    /**
     * 基于{@link ReentrantLock}和{@link Condition}实现等待通知
     */
    private Lock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();

    /**
     * 标记生产者是否生产完成所有的数据
     */
    private volatile boolean finished = false;


    /**
     * 标记是否有生产者线程在等待
     */
    private volatile boolean hasProviderWaited = false;

    /**
     * 标记是否有消费者线程在等待
     */
    private volatile boolean hasConsumerWaited = false;

    /**
     * 记录当前所有消费者线程已经消费的步数
     */
    private final AtomicLong CONSUME_STEPS = new AtomicLong(0);

    /**
     * 每个消费的步长
     */
    private final int STEP_SIZE = SIZE_PER_BUFFER >> 4;

    /**
     * 生产buffer的计数器
     */
    private final AtomicLong PRODUCE_BUFFER_COUNT = new AtomicLong(0);

    /**
     * 启动消费者
     */
    private void startConsumer(){
        for (int i = 0; i < consumerCorePoolSize; i++){
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
                            //Warn
                            currentStep = CONSUME_STEPS.getAndIncrement();
                        }
                        start = currentStep * STEP_SIZE;
                        //被消费的buffer[]的index
                        int index = (int)((start / SIZE_PER_BUFFER) % BUFFER_COUNT);
                        if (start < PRODUCE_BUFFER_COUNT.get() * SIZE_PER_BUFFER && BUFFER_FLAG[index].get()){
                            //算出终点的位置
                            end = start + STEP_SIZE - 1;
                            end %= SIZE_PER_BUFFER;
                            //算出起始位置
                            start %= SIZE_PER_BUFFER;

                            //遍历字符串
                            for (int i = (int) start; i <= end; i++){
                                //插入书
                                addString(BUFFER[index][i]);
                            }
                            currentStep = -1;

                            //消费完一个buffer，则标记为false
                            if (end == SIZE_PER_BUFFER - 1){
                                BUFFER_FLAG[index].set(false);
                            }

                            //如果有生产者等待，则通知消费者
                            if (hasProviderWaited){
                                lock.lock();
                                try {
                                    hasProviderWaited = false;
                                    full.signalAll();
                                }finally {
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
    private void startProvider(){
        for (int i = 0; i < providerCorePoolSize ; i++) {
            Runnable task = new Runnable() {
                private long index = -1;
                @Override
                public void run() {
                    while (true) {
                        if (index == -1){
                            //生产者生产数据索引, warn
                            index = PRODUCE_BUFFER_COUNT.getAndIncrement();
                        }
                        long count = CONSUME_STEPS.get() * STEP_SIZE;
                        //如果BUFFER中的数据已经被填满，则生产者进行等待
                        if (index - count / BUFFER_COUNT >= BUFFER_COUNT || BUFFER_FLAG[(int)(index%BUFFER_COUNT)].get()){
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
                            int temp = (int)(index % BUFFER_COUNT);
                            BUFFER[temp] = data;

                            //设置当前buffer为可消费
                            BUFFER_FLAG[temp].set(true);

                            index = -1;
                            //有消费者线程等待，则通知消费者线程
                            if (hasConsumerWaited){
                                lock.lock();
                                try {
                                    hasConsumerWaited = false;
                                    empty.signalAll();
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

    private final Object LOCK = new Object();

    /**
     * testing
     */
    private final AtomicLong a = new AtomicLong(1);
    /**
     * 插入字符串
     * @param str 书名
     */
    private void addString(String str){

        System.out.println("addString: " + a.getAndIncrement());
        if (str == null || str.trim().length() == 0){
            return;
        }
        //将字符串按照空格分割
        String[] strings = str.split(" ");

        String t;
        for (String s : strings){
            t = s.trim();
            if (t.length() == 0){
                continue;
            }
            //DCL
            if (statistics.get(t) == null){
                synchronized (LOCK){
                    if (statistics.get(t) == null){
                        statistics.put(t, new LongAdder());
                    }
                }
            }
            statistics.get(t).increment();
        }
    }

    private final AtomicLong atomicLong = new AtomicLong(0);

    /**
     * 获取数据的接口，可通过重写该方法实现自定义数据源
     * @return data 返回值如果为null或者length为0，则表示已获取完所有数据
     */
    protected String[] getDataFromAnyway(){
        long i =  atomicLong.incrementAndGet();
        if (i > 50){
            return null;
        }
        return new String[SIZE_PER_BUFFER];
    }

    /**
     * 阻塞主线程，直到所有任务结束
     */
    private void join(){
        while (CONSUMER_THREAD_POOL_EXECUTOR.getActiveCount() > 0){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}