package cn.shuaijunlan.java.basic.learning.volatilet;


import java.util.concurrent.TimeUnit;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 2:26 PM 2/27/19.
 */
public class VolatileTest {
    int j = 0;
    int k = 0;
    public static void main(String[] args) throws InterruptedException {
        VolatileTest volatileTest = new VolatileTest();
        new Thread(() -> volatileTest.updateJ()).start();
        TimeUnit.MILLISECONDS.sleep(1);
        Thread t = new Thread(() -> volatileTest.printK());
        t.start();
        t.join();

    }
    private void updateJ(){
        j = 10;
    }
    private void printK(){
        k = j;
        System.out.println(k);
    }
}
