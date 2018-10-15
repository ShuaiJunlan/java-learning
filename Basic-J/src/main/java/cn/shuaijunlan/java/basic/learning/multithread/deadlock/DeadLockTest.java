package cn.shuaijunlan.java.basic.learning.multithread.deadlock;

/**
 * @author Junlan Shuaijunlan[shuaijunlan@gmail.com].
 * @since Created in 4:38 PM 10/15/18.
 */
public class DeadLockTest extends Thread {
    private final String lock1;
    private final String lock2;

    public DeadLockTest(String lock1, String lock2){
        this.lock1 = lock1;
        this.lock2 = lock2;
    }
    @Override
    public void run() {
        super.run();
        synchronized (lock1){
            System.out.println(Thread.currentThread().getName() + " get lock " + lock1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2){
                System.out.println(Thread.currentThread().getName() + " get lock " + lock2);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String lock1 = "Lock1";
        String lock2 = "Lock2";
        DeadLockTest deadLockTest1 = new DeadLockTest(lock1, lock2);
        DeadLockTest deadLockTest2 = new DeadLockTest(lock2, lock1);
        deadLockTest1.start();
        deadLockTest2.start();
        deadLockTest1.join();
        deadLockTest2.join();
    }
}
