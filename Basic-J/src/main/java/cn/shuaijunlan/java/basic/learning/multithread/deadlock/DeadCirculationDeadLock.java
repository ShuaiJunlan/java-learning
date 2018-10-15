package cn.shuaijunlan.java.basic.learning.multithread.deadlock;

/**
 * @author Junlan Shuaijunlan[shuaijunlan@gmail.com].
 * @since Created in 5:06 PM 10/15/18.
 */
public class DeadCirculationDeadLock {
    private static final Object lock = new Object();


    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            synchronized (lock){
                System.out.println("Enter dead circulation");
                while (true);
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock){
                System.out.println("Get lock");
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();;
    }
}
