package com.sh.proxypattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 18:12 2017/3/6.
 */
public class GamePlayIH implements InvocationHandler
{
    Class cls = null;
    Object obj = null;
    public GamePlayIH(Object obj)
    {
        this.obj = obj;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        Object result = method.invoke(this.obj, args);
        return result;
    }
}
