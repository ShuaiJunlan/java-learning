package cn.shuaijunlan.java.basic.learning.collection;

import org.junit.Test;

import java.util.Collections;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 6:48 PM 2018/09/14.
 */
public class CollectionsTest {
    /**
     * Testing for-loop and for-each in java
     */
    @Test
    public void test1(){
        int[] arr = {1, 2, 3, 4};
        for (int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }
        for (int a : arr){
            System.out.println(a);
        }
    }
    public static void main(String[] args) {
        //private constructor
        // Collections collections = new Collections();
    }
}
