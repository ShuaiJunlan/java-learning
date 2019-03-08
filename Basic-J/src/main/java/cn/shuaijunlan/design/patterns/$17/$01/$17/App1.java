package cn.shuaijunlan.design.patterns.$17.$01.$17;

import cn.shuaijunlan.design.patterns.$17.$01.$17.test1.Test1;
import cn.shuaijunlan.design.patterns.$17.$01.$17.test1.Test2;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 12:43 2017/1/17.
 *
 * 测试Java中各种访问权限:public/protected/package/private，
 *  一个编译单元（Java文件）中至多有一个public类
 *  可以有多个非public类
 */
public class App1 extends Test1
{
    void print()
    {
//        super.printHello();
        super.getValue();
    }

//    @Override
//    void printHello()
//    {
//        System.out.println("override");
//    }

    @Override
    protected int getValue()
    {
        return 1;
    }

    public static void main(String[] args)
    {
        App1 app1 = new App1();
        app1.print();

        Test1 test1 = new Test1();
    }
}
class App2 extends Test2
{


}
