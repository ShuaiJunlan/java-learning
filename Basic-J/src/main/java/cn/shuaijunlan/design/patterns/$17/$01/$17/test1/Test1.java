package cn.shuaijunlan.design.patterns.$17.$01.$17.test1;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 12:58 2017/1/17.
 */
public class Test1
{
    /**
     * 包访问权限
     */
    void printHello()
    {
        System.out.println("hello");
    }

    /**
     * protected访问权限
     * @return
     */
    protected int getValue()
    {
        return 1;
    }

    /**
     * public访问权限
     */
    public void getAge()
    {
        System.out.println("21");
    }
    /**
     * private访问权限
     */
    private void getName()
    {
        System.out.println("shuaijunlan");
    }
}
