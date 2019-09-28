package cn.shuaijunlan.java.basic.learning.hashcode;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 8:56 AM 9/25/19.
 */
public class HashCodeTest {
    public static void main(String[] args) {
        String str1 = new String("abc");
        String str2 = new String("abc");
        System.out.println("str1 hashCode: " + str1.hashCode());
        System.out.println("str2 hashCode: " + str2.hashCode());
        System.out.println("str1 identityHashCode: " + System.identityHashCode(str1));
        System.out.println("str2 identityHashCode: " + System.identityHashCode(str2));

        User user = new User("test", 1);
        System.out.println("user hashCode: " + user.hashCode());
        System.out.println("user identityHashCode: " + System.identityHashCode(user));
    }
}
