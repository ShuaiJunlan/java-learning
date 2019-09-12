package cn.shuaijunlan.java.basic.learning.gc;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:04 AM 9/12/19.
 */
public class TestGC {
    private static TestGC testGC = null;
    private void isAlive(){
        System.out.println("Yes, I'm Alive!");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
    }

    public static void main(String[] args) throws InterruptedException {
        testGC = new TestGC();
        testGC = null;
        System.gc();
        Thread.sleep(500);
        if (testGC != null){
            testGC.isAlive();
        }else {
            System.out.println("No, I'm dead!");
        }
        testGC = null;
        System.gc();
        Thread.sleep(500);
        if (testGC != null){
            testGC.isAlive();
        }else {
            System.out.println("No, I'm dead!");
        }
    }
}
