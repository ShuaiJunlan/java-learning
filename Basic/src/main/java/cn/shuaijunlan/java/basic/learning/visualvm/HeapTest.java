package cn.shuaijunlan.java.basic.learning.visualvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 8:43 PM 11/19/18.
 */
public class HeapTest {
    static class OOMObject{
        public byte[] placeholder = new byte[1024 * 1024];
    }
    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++){
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(3000);
        fillHeap(900);
        System.gc();
        Thread.sleep(1000);
    }
}
