package cn.shuaijunlan.java.basic.learning.classloader;

/**
 * @author Junlan Shuaijunlan[shuaijunlan@gmail.com].
 * @since Created in 10:29 AM 10/14/18.
 */
public class ClassLoaderTest {
    static {
        // System.out.println(i); // Error:(9, 28) java: illegal forward reference
        i = 5;
    }
    static int i = 10;
    public static void main(String[] args) {
        System.out.println(i);
    }
}
