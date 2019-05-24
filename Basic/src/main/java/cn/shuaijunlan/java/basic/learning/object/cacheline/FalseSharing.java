package cn.shuaijunlan.java.basic.learning.object.cacheline;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 2:53 PM 5/24/19.
 *
 *
 * https://www.jianshu.com/p/c3c108c3dcfd
 *
 * https://www.cnblogs.com/cyfonly/p/5800758.html
 *
 */
public class FalseSharing implements Runnable {

    public final static int NUM_THREADS = 4; // change
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    private final int arrayIndex;

    public final static long iterations = 2;

    // private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];        //duration = 37408 ns
    private static VolatileLong2[] longs = new VolatileLong2[NUM_THREADS];   //duration = 3675 ms
    // private static VolatileLong3[] longs = new VolatileLong3[NUM_THREADS];   //duration = 3677 ms

    static {
        for (int i = 0; i < longs.length; i++) {
            // longs[i] = new VolatileLong();
            longs[i] = new VolatileLong2();
            // longs[i] = new VolatileLong3();
        }
    }

    public FalseSharing(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    private static long start = System.currentTimeMillis();

    public static void main(final String[] args) throws Exception {

        runTest();
        System.out.println("duration = " + (System.currentTimeMillis() - start) / iterations + " ms");
    }

    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));
        }
        start = System.currentTimeMillis();
        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }

    @Override
    public void run() {
        for (int k = 0; k < iterations; k++) {
            long i = ITERATIONS + 1;
            while (0 != --i) {
                longs[arrayIndex].value = i;
            }
        }
    }

    public static class Long {

    }

    public final static class VolatileLong extends Long {
        public volatile long value = 0L;
    }

    // long padding避免false sharing
    // 按理说jdk7以后long padding应该被优化掉了，但是从测试结果看padding仍然起作用
    public final static class VolatileLong2 extends Long {
        volatile long p0, p1, p2, p3, p4, p5, p6;
        // volatile long p0, p1, p3;
        public volatile long value = 0L;
        volatile long q0, q1, q2, q3, q4, q5, q6;
    }

    /**
     * jdk8新特性，Contended注解避免false sharing
     * Restricted on user classpath
     * Unlock: -XX:-RestrictContended
     */
    @sun.misc.Contended
    public final static class VolatileLong3 extends Long {
        public volatile long value = 0L;
    }
}
