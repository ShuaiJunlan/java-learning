package com.sh.$17.$01.$17.test1;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 13:34 2017/1/17.
 */
public class Test2  extends Test1
{
    public void test2()
    {
        super.printHello();
    }

    @Override
    void printHello()
    {
        System.out.println("override");
    }

    @Override
    protected int getValue()
    {
        return 1;
    }

    private void get()
    {
        Test1 test1 = new Test1();
        test1.getValue();
    }

    protected void getName()
    {
        System.out.println("shuaijunlan");
    }

    void getSex()
    {
        System.out.println("nan");
    }
}
