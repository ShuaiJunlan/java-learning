package cn.shuaijunlan.java.basic.learning.exception.memoryexception;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

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

    @Test
    public void test1() {
        new StackDepthTest().test();
    }

    /**
     * jvm arguments: -Xmx640M -Xms640M -Xmn192M -XX:MaxMetaspaceSize=128M -XX:MetaspaceSize=128M  -Xss20M
     */
    // public static void main(String[] args){
    //     List<Runnable> list = new LinkedList<>();
    //     while (true){
    //         list.add(new Thread(() -> System.out.println(1)));
    //     }
    // }
}
