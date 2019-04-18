package cn.shuaijunlan.java.basic.learning.basicdatatype.integer;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:48 AM 4/18/19.
 */
public class IntegerSwapTest {
    public static void main(String[] args) {
        Integer a = 1, b = 2;
        System.out.println("a = " + a + ", b = " + b); // output:a = 1, b = 2
        swap(a, b);
        System.out.println("a = " + a + ", b = " + b); // output:a = 2, b = 1
    }
    private static void swap(Integer a, Integer b){
        Class clazz = a.getClass();
        try {
            Class[] innerClazz = clazz.getDeclaredClasses();
            for (Class cls : innerClazz) {
                int mod = cls.getModifiers();
                String modifier = Modifier.toString(mod);
                if (modifier.contains("static")) {
                    Constructor constructor = cls.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    Object obj1 = constructor.newInstance();

                    Field field1 = cls.getDeclaredField("cache");
                    field1.setAccessible(true);
                    setValue((Integer) Array.get(field1.get(obj1), 0 + 128), 1);

                    setValue((Integer) Array.get(field1.get(obj1), 1 + 128), 2); ///????

                    setValue((Integer) Array.get(field1.get(obj1), 2 + 128), 0); ///????
                }
            }

        } catch (NoSuchFieldException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void setValue(Integer a, int t){
        Field aValue;
        try {
            aValue = a.getClass().getDeclaredField("value");
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(aValue, aValue.getModifiers() & ~Modifier.FINAL);

            if (!aValue.isAccessible()){
                aValue.setAccessible(true);
            }
            aValue.set(a, t);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    // private static void swap(Integer a, Integer b){
    //     int tempA = a, tempB = b;
    //     try {
    //         Field aValue = a.getClass().getDeclaredField("value");
    //
    //         Field modifiersField = Field.class.getDeclaredField("modifiers");
    //         modifiersField.setAccessible(true);
    //         modifiersField.setInt(aValue, aValue.getModifiers() & ~Modifier.FINAL);
    //
    //         if (!aValue.isAccessible()){
    //             aValue.setAccessible(true);
    //         }
    //         aValue.set(a, tempB);
    //
    //         Field bValue = b.getClass().getDeclaredField("value");
    //
    //         modifiersField = Field.class.getDeclaredField("modifiers");
    //         modifiersField.setAccessible(true);
    //         modifiersField.setInt(bValue, bValue.getModifiers() & ~Modifier.FINAL);
    //         if (!bValue.isAccessible()){
    //             bValue.setAccessible(true);
    //         }
    //         bValue.set(b, tempA);
    //     } catch (NoSuchFieldException | IllegalAccessException e) {
    //         e.printStackTrace();
    //     }
    // }

    // private static void swap3(Integer a, Integer b){
    //     Field[] fields = a.getClass().getDeclaredFields();
    //     for (Field field : fields) {
    //         field.setAccessible(true);
    //         Object temp = null;
    //         try {
    //             temp = field.get(a);
    //             field.set(a, field.get(b));
    //             field.set(b, temp);
    //         } catch (IllegalAccessException e) {
    //             e.printStackTrace();
    //         }
    //
    //     }
    // }


}
