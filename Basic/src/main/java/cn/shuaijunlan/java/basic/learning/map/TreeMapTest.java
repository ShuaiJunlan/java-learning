package cn.shuaijunlan.java.basic.learning.map;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 1:41 PM 1/18/19.
 */
public class TreeMapTest {
    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(5, "green");

        treeMap.put(1, "red");
        treeMap.put(3, "yellow");
        treeMap.put(4, "white");
        treeMap.put(2, "black");

        for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
