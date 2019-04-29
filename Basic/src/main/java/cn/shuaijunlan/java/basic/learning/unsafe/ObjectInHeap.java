package cn.shuaijunlan.java.basic.learning.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 2:51 PM 3/15/19.
 */
public class ObjectInHeap {
    private long address = 0;

    public static Unsafe getUnsafe() {

        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        try {
            return (Unsafe) unsafeField.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Unsafe unsafe = getUnsafe();

    public ObjectInHeap()
    {
        // address = unsafe.allocateMemory(2 * 1024 * 1024);
    }

    // Exception in thread "main" java.lang.OutOfMemoryError
    public static void main(String[] args)
    {
        while (true)
        {
            ObjectInHeap heap = new ObjectInHeap();
            System.out.println("memory address=" + heap.address);
        }
    }
}
