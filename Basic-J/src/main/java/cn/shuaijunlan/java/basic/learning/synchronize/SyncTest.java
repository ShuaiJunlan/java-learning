package cn.shuaijunlan.java.basic.learning.synchronize;

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

}
class TestReentrant{
    public synchronized void printName(){
        System.out.println("Shuai Junlan");
        printAge();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
    public synchronized void printAge(){

        System.out.println(22);

    }
    public synchronized void printNumber(){
        System.out.println(110);
    }
}
