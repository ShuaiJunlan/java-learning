package cn.shuaijunlan.java.basic.learning.jni;

/**
 * @author Junlan Shuaijunlan[shuaijunlan@gmail.com].
 * @since Created in 3:24 PM 10/9/18.
 */
public class JNITest {
    static {
        System.loadLibrary("hello_JNI");
    }

    public static void main(String[] args) {
        System.out.println(testJNI());
    }
    public static native String testJNI();
}
