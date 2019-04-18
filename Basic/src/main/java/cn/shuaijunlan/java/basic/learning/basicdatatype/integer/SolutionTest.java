package cn.shuaijunlan.java.basic.learning.basicdatatype.integer;

import java.lang.reflect.Field;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 2:19 PM 4/18/19.
 */
public class SolutionTest {
    public static void main(String[] args) {
        Integer a = 1, b = 2;
        System.out.println("a = " + a + ", b = " + b); // output:a = 1, b = 2
        swap(a, b);
        System.out.println("a = " + a + ", b = " + b); // output:a = 2, b = 1
    }

    private static void swap(Integer a, Integer b) {
        try {
            Field valueField = Integer.class.getDeclaredField("value");
            valueField.setAccessible(true);
            int t = a;
            valueField.set(a, b);
            valueField.set(b, new Integer(t));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
