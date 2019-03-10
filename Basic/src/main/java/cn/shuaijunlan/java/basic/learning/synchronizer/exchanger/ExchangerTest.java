package cn.shuaijunlan.java.basic.learning.synchronizer.exchanger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 4:45 PM 11/8/18.
 */
public class ExchangerTest {


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Exchanger<String> exchanger = new Exchanger<>();

        new ExchangeThreadName("1", exchanger, countDownLatch).start();
        new ExchangeThreadName("2", exchanger, countDownLatch).start();
        new ExchangeThreadName("3", exchanger, countDownLatch).start();
        countDownLatch.countDown();

        exchanger.exchange("Only one");
    }
}
class ExchangeThreadName extends Thread{
    private String mark;
    private Exchanger<String> exchanger;
    private CountDownLatch countDownLatch;
    ExchangeThreadName(String mark, Exchanger<String> exchanger, CountDownLatch countDownLatch){
        this.mark = mark;
        this.exchanger = exchanger;
        this.countDownLatch = countDownLatch;
    }
    @Override
    public void run() {
        try {
            countDownLatch.await();
            System.out.println(mark + ":" + Thread.currentThread().getName());
            String val = exchanger.exchange(Thread.currentThread().getName());
            System.out.println(mark + ":" + val);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
