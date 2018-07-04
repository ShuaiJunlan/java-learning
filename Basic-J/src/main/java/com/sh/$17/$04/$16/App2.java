package com.sh.$17.$04.$16;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 16:21 2017/4/16.
 */
public class App2
{
    public static void main(String[] args)
    {
        try
        {
            Class obj = Class.forName("com.sh.$17.$04.$16.App1");
            Field[] fields = obj.getFields();
            String a = Modifier.toString(fields[0].getModifiers());
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
