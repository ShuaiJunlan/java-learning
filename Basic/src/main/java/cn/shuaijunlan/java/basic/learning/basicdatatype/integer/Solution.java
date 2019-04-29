package cn.shuaijunlan.java.basic.learning.basicdatatype.integer;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 11:36 AM 4/18/19.
 */
public class Solution {
    public static void main(String[] args) {
        Integer a = 1, b = 2;
        System.out.println("a = " + a + ", b = " + b); // output:a = 1, b = 2
        swap(a, b);
        System.out.println("a = " + a + ", b = " + b); // output:a = 2, b = 1
    }
    private static void swap(Integer a, Integer b){
        // writing your code to here
        Integer temp = 3;
        try {
            Field aValue = a.getClass().getDeclaredField("value");

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);

            modifiersField.setInt(aValue, aValue.getModifiers() & ~Modifier.FINAL);

            if (!aValue.isAccessible()){
                aValue.setAccessible(true);
            }
            aValue.set(a, 1);

            Field bValue = b.getClass().getDeclaredField("value");

            modifiersField.setInt(bValue, bValue.getModifiers() & ~Modifier.FINAL);
            if (!bValue.isAccessible()){
                bValue.setAccessible(true);
            }
            bValue.set(b, 3);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
