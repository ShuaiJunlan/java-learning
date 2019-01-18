package cn.shuaijunlan.java.basic.learning.exception.memoryexception;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 4:00 PM 1/16/19.
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        int i = 0 ;
        try {
            while (true){
                // unsafe.allocateMemory(_1MB);
                // if (i++ == 10000){
                //     System.exit(1);
                // }
            }
        }finally {
            System.out.println(i);
        }

    }
}
