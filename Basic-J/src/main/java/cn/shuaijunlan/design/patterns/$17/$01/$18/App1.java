package cn.shuaijunlan.design.patterns.$17.$01.$18;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 10:46 2017/1/18.
 *
 * 1)试@ThreadLocalMap和@PrintWriter
 * 2)测试方法重载和方法重写
 */
public class App1
{
    public static void main(String[] args)
    {
        //  1)start
        ThreadLocal threadLocal = new ThreadLocal();
        try
        {
            PrintWriter printWriter = new PrintWriter(new String());
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
        }
        //  1)the end

        ((App1)null).printUser();



    }

    //  2)start
    public void getValue(String name)
    {

    }

    public int getVlaue(int a)
    {
        return a;
    }
    public final static void getValue(int name)
    {

    }
    //  2)the end


    //  3)start
    private static void printUser()
    {
        System.out.println("hello");
    }
    //  3)the end
}
