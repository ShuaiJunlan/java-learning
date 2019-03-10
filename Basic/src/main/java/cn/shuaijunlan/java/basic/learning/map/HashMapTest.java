package cn.shuaijunlan.java.basic.learning.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Junlan Shuaijunlan[shuaijunlan@gmail.com].
 * @since Created in 10:52 AM 10/12/18.
 */
public class HashMapTest {
    @Test
    public void test1(){
        Map<Integer, String> map = new HashMap<>();
        Map<Integer, String> map1 = new ConcurrentHashMap<>();
    }
}
