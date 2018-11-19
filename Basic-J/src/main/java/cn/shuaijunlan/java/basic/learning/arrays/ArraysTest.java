package cn.shuaijunlan.java.basic.learning.arrays;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:26 AM 11/19/18.
 */
public class ArraysTest {
    @Test
    public void test1(){
        String[] a = {"aa", "bb", "cc"};
        List<String> list = Arrays.asList(a);
        System.out.println(list.size());
        System.out.println(list.toString());
    }

    /**
     * why?
     */
    @Test
    public void test2(){
        int[] i = {1, 2, 3};
        List list = Arrays.asList(i);
        System.out.println(list.size());
        System.out.println(list.toString());
    }

    /**
     * so?
     */
    @Test
    public void test3(){
        Integer[] i = {11, 22, 33};
        List list = Arrays.asList(i);
        System.out.println(list.size());
        System.out.println(list.toString());
    }

}
