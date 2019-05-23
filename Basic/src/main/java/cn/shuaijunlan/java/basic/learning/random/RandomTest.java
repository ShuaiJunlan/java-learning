package cn.shuaijunlan.java.basic.learning.random;

import org.junit.Test;

import java.util.Random;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:58 AM 5/16/19.
 */
public class RandomTest {
    private Random rng = new Random(2019);
    @Test
    public void test1(){
        for (int i = 0; i < 100; i++){
            System.out.println(rng.nextDouble());
        }
    }

    @Test
    public void test2(){
        for (int i = 0; i < 1000; i++){
            System.out.println(nextRTT());
        }

    }
    private long nextRTT() {
        double u = rng.nextDouble();
        int x = 0;
        double cdf = 0;
        while (u >= cdf) {
            x++;
            cdf = 1 - Math.exp(-1.0D * 1 / 60 * x);
        }
        return x;
    }
}
