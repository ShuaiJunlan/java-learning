package cn.shuaijunlan.java.basic.learning.concurrent.thread;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 6:33 PM 3/20/19.
 */
public class ThreadJoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
            }
        });
        thread.join();
    }
}
