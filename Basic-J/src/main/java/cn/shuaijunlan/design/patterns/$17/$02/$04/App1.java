package cn.shuaijunlan.design.patterns.$17.$02.$04;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 16:38 2017/2/4.
 */
public class App1
{
    public static void main(String[] args)
    {
        boolean result;
        result = true;
        if (result)
        {
        }

        int a = 5;
        int b = 10;
        System.out.println(~b);
        System.out.println(a + ~b);

        String s = null;
        if ((s != null) & (s.length() > 0))
        {
            System.out.println("hello");
        }
    }
}
