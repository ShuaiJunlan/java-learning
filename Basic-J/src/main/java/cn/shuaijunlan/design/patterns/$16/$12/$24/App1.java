package cn.shuaijunlan.design.patterns.$16.$12.$24;

/**
 * Created by Mr SJL on 2016/12/24.
 *
 * @Author Junlan Shuai
 */
public class App1
{
    /**
     * 测试字符数组和字符串
     * @param args
     */
    public static void main(String[] args)
    {
        char a[] = {'a', 'b','c'};
        String b = "abc";
        System.out.println(a.equals(b));
        System.out.println(a.length == b.length());
    }
}
