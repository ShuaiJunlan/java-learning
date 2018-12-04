package cn.shuaijunlan.java.basic.learning.concurrent.volatiles;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 7:47 PM 12/4/18.
 */
public class NonVolatileTest {
    private boolean stop = false;
    // private volatile boolean stop = false;
    public static void main(String[] args) throws InterruptedException {
        NonVolatileTest nonVolatileTest = new NonVolatileTest();

        Thread thread = new Thread(nonVolatileTest.getTask());
        thread.start();
        Thread.sleep(1000);
        System.out.println("Set stop to true");
        nonVolatileTest.stop = true;
        System.out.println("Exit main.");

    }
    Task getTask(){
        return new Task();
    }
    private class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("Task started!");

            while (!stop){

            }
            System.out.println("Task stopped!");
        }
    }
}
