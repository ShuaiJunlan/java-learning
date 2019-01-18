package cn.shuaijunlan.java.basic.learning.exception.memoryexception;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 3:32 PM 1/16/19.
 */
public class StackDepthTest {
    private int count = 0;
    public void testAdd(){
        count++;
        testAdd();
    }
    public void test(){
        try {
            testAdd();
        }catch (Throwable e){
            System.out.println(e);
            System.out.println(count);
        }
    }

    public static void main(String[] args) {
        new StackDepthTest().test();
    }
}
