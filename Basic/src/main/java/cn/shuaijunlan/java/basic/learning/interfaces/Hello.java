package cn.shuaijunlan.java.basic.learning.interfaces;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 3:03 PM 12/9/18.
 */
public class Hello implements ConstantInterface, ConstantInterface1 {
    public static void main(String[] args) {
        System.out.println(ConstantInterface.a);
    }
}
