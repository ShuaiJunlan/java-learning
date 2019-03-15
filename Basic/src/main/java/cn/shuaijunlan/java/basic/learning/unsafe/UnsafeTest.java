package cn.shuaijunlan.java.basic.learning.unsafe;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 2:14 PM 3/15/19.
 */
public class UnsafeTest {
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

    /**
     * 获取字段值
     */
    @Test
    public void testObject(){
        try {
            Unsafe u = getUnsafe();
            Class<?> tk = A.class;
            A a = new A();

            // 下面这些偏移量实际都可以用常量来表示，在一次程序运行过程中它们是不变的
            long offset = u.objectFieldOffset(tk.getDeclaredField("x"));
            System.err.println(u.getInt(a, offset)); // 读取x

            long finalOffset = u.objectFieldOffset(tk.getDeclaredField("finalX"));
            System.err.println(u.getInt(a, finalOffset)); // 读取finalX 233
            u.putInt(a, finalOffset, 100); // 可以用来更改final的值
            System.err.println(u.getInt(a, finalOffset)); // 读取finalX 100

            Object staticBase = u.staticFieldBase(tk.getDeclaredField("staticX")); // 这里就是返回 A.class，跟逻辑是一致
            long staticOffset = u.staticFieldOffset(tk.getDeclaredField("staticX"));
            System.err.println(u.getInt(staticBase, staticOffset));

            long staticFinalOffset = u.staticFieldOffset(tk.getDeclaredField("staticFinalX"));
            System.err.println(u.getInt(staticBase, staticFinalOffset));

            System.err.println(A.class == staticBase); // true
            System.err.println(u.getInt(A.class, staticOffset)); // 等价于u.getInt(staticBase, staticOffset)
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 岁数组进行CAS操作
     */
    @Test
    public void testArray(){
        Unsafe unsafe = getUnsafe();
        int[] ints = {13, 17, 19, 23, 29, 31, 37};
        for (int i = 0; i < ints.length; i++) {
            unsafe.compareAndSwapInt(ints, (long)(Unsafe.ARRAY_INT_BASE_OFFSET + Unsafe.ARRAY_INT_INDEX_SCALE * i), ints[i], i);
            System.out.println(unsafe.getInt(ints, (long)(Unsafe.ARRAY_INT_BASE_OFFSET + Unsafe.ARRAY_INT_INDEX_SCALE * i)));
        }

        Boolean[] booleans = new Boolean[10];
        for (int i = 0; i < booleans.length; i++){
            unsafe.compareAndSwapObject(booleans, (long)(Unsafe.ARRAY_BOOLEAN_BASE_OFFSET + Unsafe.ARRAY_BOOLEAN_INDEX_SCALE * i), booleans[i], Boolean.TRUE);
            System.out.println(unsafe.getBoolean(booleans, (long)(Unsafe.ARRAY_BOOLEAN_BASE_OFFSET + Unsafe.ARRAY_BOOLEAN_INDEX_SCALE * i)));
        }
    }

    @Test
    public void testMemory() throws InterruptedException {
        Unsafe unsafe = getUnsafe();
        int MB = 1024 * 1024;
        int GB = MB * 1024;
        while (true){
            long address = unsafe.allocateMemory(GB * 4);

        }

        // Thread.sleep(100000);
    }
}
