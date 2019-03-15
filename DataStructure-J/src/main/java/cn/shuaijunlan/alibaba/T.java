package cn.shuaijunlan.alibaba;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class T {
    private static boolean[] a = new boolean[4];

    public static final Unsafe UNSAFE;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws NoSuchFieldException {
        T t = new T();
        t.test3();

    }
    public void test() throws NoSuchFieldException {
        System.out.println(1 << 14);
        System.out.println(Runtime.getRuntime().availableProcessors());

        long longArrayOffset = UNSAFE.arrayBaseOffset(boolean[].class);


        // int index = a.length-1;

        UNSAFE.compareAndSwapObject(a, UNSAFE.objectFieldOffset(T.class.getDeclaredField("a")), false, true);
        System.out.println(Arrays.toString(a));
    }
    public void test2(){
        AtomicBoolean[] a = new AtomicBoolean[2];
        for (int i = 0; i < a.length; i++){
            a[i] = new AtomicBoolean(false);
        }
        System.out.println(a[0]);
    }

    private void test3(){
        String a = new String("asdf adf af asdf  df    ");
        System.out.println(Arrays.toString(a.split(" ")));
        System.out.println(a.split(" ").length);
    }
}
