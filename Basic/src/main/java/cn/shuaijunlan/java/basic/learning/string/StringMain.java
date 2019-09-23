package cn.shuaijunlan.java.basic.learning.string;

import org.junit.Test;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 8:08 PM 2018/08/12.
 */
public class StringMain {
    public static void main(String[] args) {
        final String a = "123";
        // a = "345";
        System.out.println(a);
    }

    @Test
    public void test1(){
        String a = new String("aabbbcc");
        changeStr(a);
        System.out.println(a);
    }
    private void changeStr(String a){
        if (a.indexOf("a") >= 0){
            a.replace('a', 'b');
            a = "bbb";
        }
    }

    @Test
    public void test2(){
        String a = "111";
        String b = new String("111");
    }

    @Test
    public void test3(){
        String s1=new String("kvill");

        String s2=s1.intern();

        System.out.println( s1==s1.intern() );

        System.out.println( s1+" "+s2 );

        System.out.println( s2==s1.intern() );
    }

    @Test
    public void test4(){
        String s1 = "aa";
        String s2 = "aa";
        System.out.println(s1 == s2);
    }

}
