package cn.shuaijunlan.java.basic.learning.container;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 5:33 PM 2018/09/09.
 */
public class ArrayListTest {
    /**
     * This will throw ConcurrentModificationException
     */
    @Test
    public void test1() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(i);
        }
        //throw ConcurrentModificationException
        for (Integer a : arrayList) {
            arrayList.remove(a);
        }
        //positive way
        Iterator<Integer> integerIterator = arrayList.iterator();
        while (integerIterator.hasNext()){
            integerIterator.remove();
        }
    }
    @Test
    public void test2(){
        ArrayList<Integer> arrayList = new ArrayList<>(16);
        System.out.println(arrayList.size());
        arrayList.add(1);
        System.out.println(arrayList.get(2));
    }
}
