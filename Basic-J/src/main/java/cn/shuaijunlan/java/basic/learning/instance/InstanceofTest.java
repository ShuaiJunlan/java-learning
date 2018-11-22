package cn.shuaijunlan.java.basic.learning.instance;


/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 11:05 AM 11/22/18.
 */
public class InstanceofTest {
    public static void main(String[] args) {
        Object o1 = new int[]{};
        Object o2 = new int[][]{};
        Object o3 = new String[]{};

        System.out.print(o1 instanceof Object[]);
        System.out.print(o2 instanceof Object[]);
        System.out.print(o3 instanceof Object[]);

    }
}
