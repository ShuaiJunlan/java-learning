package cn.shuaijunlan.java.basic.learning.container;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 8:36 PM 2/22/19.
 */
public class LinkedHashMapTest {
    @Test
    public void test1(){
        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(1,1);
        linkedHashMap.put(2,2);
        linkedHashMap.put(1,3);
        Iterator<Map.Entry<Integer, Integer>> entryIterator = linkedHashMap.entrySet().iterator();
        while (entryIterator.hasNext()){
            System.out.println(entryIterator.next().getKey());
        }
    }
}
