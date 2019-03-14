package cn.shuaijunlan.alibaba1;

import java.util.concurrent.*;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 3:11 PM 3/14/19.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println((int)'z');//122
        System.out.println((int)'a');//97
        System.out.println((char)96);
        System.out.println((int)'Z');//90
        System.out.println((int)'A');//65

        final ThreadPoolExecutor PROVIDER_THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(2,
                2, 0, TimeUnit.MICROSECONDS, new SynchronousQueue<>(), new ThreadFactory() {

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

        PROVIDER_THREAD_POOL_EXECUTOR.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(PROVIDER_THREAD_POOL_EXECUTOR.getActiveCount());

    }
}
