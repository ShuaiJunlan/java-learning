package cn.shuaijunlan.java.basic.learning.concurrent.volatiles;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 7:47 PM 12/4/18.
 */
public class NonVolatileWithObjectTest {
    private volatile boolean stop = false;
    // private boolean stop = false;
    public static void main(String[] args) throws InterruptedException {
        NonVolatileWithObjectTest nonVolatileTest = new NonVolatileWithObjectTest();

        Thread thread = new Thread(nonVolatileTest.getTask(nonVolatileTest));
        thread.start();
        Thread.sleep(1000);
        System.out.println("Set stop to true");
        nonVolatileTest.stop = true;
        System.out.println("Exit main.");

    }
    Task getTask(NonVolatileWithObjectTest nonVolatileWithObjectTest){
        return new Task(nonVolatileWithObjectTest);
    }
    private class Task implements Runnable {
        NonVolatileWithObjectTest nonVolatileWithObjectTest;
        Task(NonVolatileWithObjectTest nonVolatileWithObjectTest){
            this.nonVolatileWithObjectTest = nonVolatileWithObjectTest;
        }

        @Override
        public void run() {
            System.out.println("Task started!");

            while (!nonVolatileWithObjectTest.stop){

            }
            System.out.println("Task stopped!");
        }
    }
}
