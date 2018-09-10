package cn.shuaijunlan.netty.fastthreadlocal;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 10:10 AM 2018/09/10.
 */
public class FastThreadLocalTest {
    /**
     * Difference between FastThreadLocal and ThreadLocal
     */
    @Test
    public void test1(){
        FastThreadLocal<Integer> local1 = new FastThreadLocal<>();
        FastThreadLocal<Integer> local2 = new FastThreadLocal<>();
        local1.set(1);
        local1.set(2);
        System.out.println(local1.get());
        System.out.println(local2.get());


        ThreadLocal<Integer> threadLocal1 = new ThreadLocal<>();
        ThreadLocal<Integer> threadLocal2 = new ThreadLocal<>();
        threadLocal1.set(1);
        threadLocal2.set(2);
        System.out.println(threadLocal1.get());
        System.out.println(threadLocal2.get());


    }
    @Test
    public void test2() throws InterruptedException {
        Runnable runnable = () -> {
            FastThreadLocal.removeAll();
            FastThreadLocal<Integer> local1 = new FastThreadLocal<>();
            FastThreadLocal<Integer> local2 = new FastThreadLocal<>();
            local1.set(1);
            local2.set(2);
            System.out.println(local1.get());
            System.out.println(local2.get());
        };

        FastThreadLocalThread threadLocalThread = new FastThreadLocalThread(runnable);
        threadLocalThread.start();
        threadLocalThread.join();

    }
}
