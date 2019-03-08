package cn.shuaijunlan.design.patterns.$17.$03.$28;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 10:41 2017/3/28.
 */
public class App1
{
    //    private finalp
    public static void main(String[] args)
    {
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(waysToTop(10));
    }
    public static int waysToTop(int n)
    {
        if (n <= 0)
            return 0;
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        if (n == 3)
            return 4;
        return waysToTop(n - 1) + waysToTop(n - 2) + waysToTop(n - 3);
    }
}
