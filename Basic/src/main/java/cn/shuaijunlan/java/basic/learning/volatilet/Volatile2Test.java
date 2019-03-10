package cn.shuaijunlan.java.basic.learning.volatilet;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 2:38 PM 2/27/19.
 */
public class Volatile2Test {
    private static boolean ready;
    private static int number;
    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while(!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }
    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }

}
