package cn.shuaijunlan.java.basic.learning.synchronizer.exchanger;

import java.util.concurrent.Exchanger;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 4:45 PM 11/8/18.
 */
public class ExchangerTest {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new ExchangeThreadName("1", exchanger).start();
        new ExchangeThreadName("2", exchanger).start();
    }
}
class ExchangeThreadName extends Thread{
    private String mark;
    private Exchanger<String> exchanger;
    ExchangeThreadName(String mark, Exchanger<String> exchanger){
        this.mark = mark;
        this.exchanger = exchanger;
    }
    @Override
    public void run() {
        try {
            System.out.println(mark + ":" + Thread.currentThread().getName());
            String val = exchanger.exchange(Thread.currentThread().getName());
            System.out.println(mark + ":" + val);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
