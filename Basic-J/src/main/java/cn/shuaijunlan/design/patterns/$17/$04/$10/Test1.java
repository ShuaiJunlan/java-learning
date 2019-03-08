package cn.shuaijunlan.design.patterns.$17.$04.$10;

import java.lang.reflect.Field;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 21:44 2017/4/10.
 */
public class Test1
{
    public static void main(String[] args)
    {
        App1 app1 = new App1();
        app1.printName();
        try
        {
            Field field = app1.getClass().getDeclaredField("name");
            field.setAccessible(true);
            field.set(app1, "Junlan Shuai");
            app1.printName();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
