package cn.shuaijunlan.design.patterns.spring.aop.advice;


import java.lang.reflect.Method;
import org.springframework.aop.AfterReturningAdvice;

/**
 * Created by Mr SJL on 2016/10/28.
 */
public class HijackAfterMethod implements AfterReturningAdvice
{
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable
    {
        System.out.println("HijackAfterMethod : After method hijacked!");
    }
}
