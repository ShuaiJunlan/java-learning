package cn.shuaijunlan.java.basic.learning.object;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:55 AM 1/9/19.
 *
 * TODO: https://blog.csdn.net/mfjiyi/article/details/17270557
 */
public class GettingClasses {
    public static void main(String[] args) throws ClassNotFoundException {
        Integer integer = new Integer(1);
        Class<Integer> clazz1 = (Class<Integer>) integer.getClass();
        Class<Integer> clazz2 = Integer.class;
        Class<Integer> clazz3 = (Class<Integer>) Class.forName("java.lang.Integer");
        System.out.println(clazz1.getName());
        System.out.println(clazz2.getName());
        System.out.println(clazz3.getName());
    }
}
