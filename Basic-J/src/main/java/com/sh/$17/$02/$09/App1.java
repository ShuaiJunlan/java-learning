package com.sh.$17.$02.$09;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 10:33 2017/2/9.
 */
public class App1
{
    public void foo(int[][] a)
    {
//        a++;
        a[1][1] = 9;
    }
    public static void main(String[] args)
    {
        long a = 0xf000000000000000l;
        long b = 0x7fffffffffffffffl;
        long c = 8070450532247928833l;
        long d = (long)(Math.pow(2,62) + Math.pow(2, 61) + Math.pow(2, 60) + 1);

        System.out.println((a-b) == d);
    }
}
