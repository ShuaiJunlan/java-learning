package cn.shuaijunlan.java.basic.learning.container;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 2:46 PM 2/22/19.
 *
 * Questions:
 * 1.if add an node, the list treeify to tree, then the amount of the nodes of the bin?
 */
public class HashMapTest {
    @Test
    public void test1(){
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put(null, 13);
        System.out.println(hashMap.get(null));
    }

    @Test
    public void test2(){
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.putIfAbsent("s", null);
        System.out.println(hashMap.get("s"));
        hashMap.putIfAbsent("s", 1);
        System.out.println(hashMap.get("s"));

        hashMap.putIfAbsent("h", 3);
        System.out.println(hashMap.get("h"));
        hashMap.putIfAbsent("h", 4);
        System.out.println(hashMap.get("h"));

    }

    @Test
    public void test3(){
        HashMap<String, Integer> hashMap = new HashMap<>(20);
        hashMap.put("1", 1);
        hashMap.put("2", 2);
        hashMap.put("3", 3);
        hashMap.put("4", 4);
        hashMap.put("5", 5);
        hashMap.put("7", 7);
        hashMap.put("6", 6);
        Iterator<Map.Entry<String, Integer>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().getKey());
        }
    }

}
