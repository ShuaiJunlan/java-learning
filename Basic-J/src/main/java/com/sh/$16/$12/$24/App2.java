package com.sh.$16.$12.$24;

/**
 * Created by Mr SJL on 2016/12/24.
 *
 * @Author Junlan Shuai
 */
public class App2
{
    public static void main(String[] args)
    {
        int a = 10;
        int b = 10;
        Integer c = new Integer(10);
        Integer d = Integer.valueOf(10);
        Integer e = 2000;
        Integer f = 2000;

        System.out.println("a=b:" +(a==b));
        System.out.println("a=c:" +(a==c));
        System.out.println("a=d:" +(a==d));
        System.out.println("c=d:" +(c==d));
        System.out.println("e=f:" +(e==f));
    }
}
