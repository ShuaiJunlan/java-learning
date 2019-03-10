package cn.shuaijunlan.java.basic.learning.synchronize;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 6:34 PM 11/18/18.
 */
public class SyncTest {
    public static void main(String[] args) throws InterruptedException {
        final TestReentrant a = new TestReentrant();
        Thread thread1 = new Thread(() -> a.printName());
        Thread thread2 = new Thread(() -> a.printNumber());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

    }
    @Test
    public void test1() throws InterruptedException {
        TestReentrant a = new TestReentrant();
        TestReentrant b = new TestReentrant();
        Thread thread = new Thread(() ->
        {
            try {
                a.printMsg();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        , "a");
        Thread thread1 = new Thread(() -> {
            try {
                b.printMsg();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "b");
        thread.start();
        TimeUnit.MILLISECONDS.sleep(100);
        thread1.start();

        thread.join();
        thread1.join();
    }

}
class TestReentrant{
    synchronized void printName(){
        System.out.println("Shuai Junlan");
        printAge();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
    private synchronized void printAge(){

        System.out.println(22);

    }
    synchronized void printNumber(){
        System.out.println(110);
    }
    void printMsg() throws InterruptedException {
        synchronized (TestReentrant.class){
            System.out.println("Hello： " + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(3);
        }
    }
}
