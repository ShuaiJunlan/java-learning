package cn.shuaijunlan.design.patterns.spring.aop.advice;

import org.springframework.aop.ThrowsAdvice;

/**
 * Created by Mr SJL on 2016/10/28.
 */
public class HijackThrowException implements ThrowsAdvice
{
    public void afterThrowing(IllegalArgumentException e) throws Throwable {
        System.out.println("HijackThrowException : Throw exception hijacked!");
    }
}
