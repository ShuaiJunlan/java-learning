package cn.shuaijunlan.design.patterns.$17.$01.$20;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 19:37 2017/1/20.
 */
public class App1
{
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : n + 1;
    }

    public static void main(String[] args)
    {
        System.out.println(tableSizeFor(10));
        System.out.println(8>>>1);
        System.out.println(8>>1);
        int[] a = new int[10];
        System.out.println(a.getClass().toString());

    }
}
