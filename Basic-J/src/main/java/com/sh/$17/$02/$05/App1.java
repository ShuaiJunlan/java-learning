package com.sh.$17.$02.$05;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 11:51 2017/2/5.
 */
public class App1 extends Test1
{
    @Override
    public void method1()
    {
        System.out.println("method1");
    }
    public void method2()
    {
        System.out.println("method2");
    }
    public static void main(String[] args)
    {
        Test1 test1 = new App1();
        test1.method1();
    }
}
