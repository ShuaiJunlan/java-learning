package cn.shuaijunlan.java.basic.learning.math;

import org.junit.Test;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 1:53 PM 1/11/19.
 *
 *
 */
public class Test1 {
    public static void main(String[] args) {
        int x = -5;
        int y = -12;
        System.out.println(y % x);
    }
    @Test
    public void test1(){
        float t = 5.1f;
        int i = (byte)t;
        System.out.println(i);
    }
    @Test
    public void test2(){
        int a = 1;
        System.out.println(a++ + ++a);
    }

    /**
     * TODO： https://blog.csdn.net/fstar_/article/details/70952756
     */
    @Test
    public void test3(){
        System.out.println(((-1) % 10));
        System.out.println(((-5) % 3));
        System.out.println(((5) % -3));
        System.out.println(((-5) % -3));
    }

    /**
     * TODO: https://www.nowcoder.com/questionTerminal/a568949ffbfc4a20977a2dab786032dd
     *  公式-n=~n+1可推出~n=-n-1，所以~10=-11再加5结果为-6
     */
    @Test
    public void test4(){
        int i = 5;
        int j = 10;
        System.out.println(i + ~j);
    }
    @Test
    public void test5(){
        int h;
        Object key = new Object();
        System.out.println((h = key.hashCode()) ^ (h >>> 16));
    }

    @Test
    public void test6(){
        System.out.println(Math.round(1.5));
    }
}
