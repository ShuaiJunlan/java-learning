package cn.shuaijunlan.design.patterns.thread.threewayscreatethread;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.concurrent.*;


/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 19:45 2017/4/10.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class Test1 {
    @Test
    public void test1() {
        Thread thread = new ExtendThread("Junlan Shuai");
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        Thread thread = new Thread(new ImplRunnable("Junlan Shuai"));
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        ImplCallable implCallable = new ImplCallable("Junlan Shuai");
        ExecutorService es = Executors.newFixedThreadPool(3);
        Future future = es.submit(implCallable);
        try {
            System.out.println(future.get());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
