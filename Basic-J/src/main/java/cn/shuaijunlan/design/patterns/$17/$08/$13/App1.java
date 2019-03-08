package cn.shuaijunlan.design.patterns.$17.$08.$13;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 8:50 2017/8/13.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class App1 {
    @Test
    public void test1(){
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            integerList.add(i);
        }
        int temp = integerList.size() >> 1;
        for (int i = 0; i < temp; i++){
            integerList.remove(i);
        }
//        for (Iterator<Integer> integerIterator = integerList.iterator(); integerIterator.hasNext();){
//            Integer i = integerIterator.next();
//            integerIterator.remove();
//        }
        for (ListIterator<Integer> integerListIterator = integerList.listIterator(); integerListIterator.hasNext();){
            integerList.add(1);
            if (integerList.size() == 10) break;
        }
        System.out.println("size:" + integerList.size());
//        for (Integer i : integerList){              //  java.util.ConcurrentModificationException
//            integerList.remove(i);
//        }
    }
}
