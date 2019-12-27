package cn.shuaijunlan.java.basic.learning.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 8:30 AM 9/18/19.
 */
public class ArrayToListTest {
    @Test
    public void test1(){
        int[] test1 = new int[]{1,2,3,4};
        Stream.of(test1).forEach(System.out::println);
        // System.out.println(List.of());

        Integer[] test2 = new Integer[]{1,2,3,4};
        Stream.of(test2).forEach(System.out::println);

        List<Integer> list = new ArrayList<>();
        // Collections.addAll(list, test1);
        Collections.addAll(list, test2);
        list.forEach(System.out::print);
    }
}
