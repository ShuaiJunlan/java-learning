package cn.shuaijunlan.java.basic.learning.basicdatatype.aADDb;

import org.junit.Test;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 11:57 AM 10/24/18.
 */
public class aADDbTest {
    @Test
    public void test1() {
        int a = 3, b = 10;
        a = a + b;
        System.out.println(a);
    }

    public void test2() {
        int a = 3, b = 10;
        a += b;
        System.out.println(a);

    }

    /**
     * A compound assignment expression of the form E1 op= E2 is equivalent to E1 = (T) ((E1) op (E2)),
     * where T is the type of E1, except that E1 is evaluated only once.
     */
    @Test
    public void test3() {
        byte a = 1;
        int b = 2;

        a += b; // compiles
        // a = a + b; // doesn't compile as a byte + int = int
        a = (byte) (a + b); // compiles as this is the same as +=


    }

    public void test4() {
        //for more examples
        int a = 5;
        a += 1.5f;
        // a == 6

        char ch = '0'; // (char) 49
        ch *= 1.1;     // ch = '4';

        long l = Integer.MAX_VALUE;
        l += 0.0f;   // i = (long ) ((long ) l + 0.0f)
        // i == Integer.MAX_VALUE + 1L; WTF!?
        // l is no longer Integer.MAX_VALUE due to rounding error.
    }
}
