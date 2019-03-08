package cn.shuaijunlan.design.patterns.spring.aop.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by Mr SJL on 2016/10/28.
 */
public class HijackBeforeMethod implements MethodBeforeAdvice
{

    public void before(Method method, Object[] objects, Object o) throws Throwable
    {
        System.out.println("HijackBeforeMethod : Before method hijacked!");
    }
}
