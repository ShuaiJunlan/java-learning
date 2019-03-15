package cn.shuaijunlan.java.basic.learning.unsafe;

import sun.misc.Unsafe;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 2:51 PM 3/15/19.
 */
public class ObjectInHeap {
    private long address = 0;

    private Unsafe unsafe = UnsafeTest.getUnsafe();

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
