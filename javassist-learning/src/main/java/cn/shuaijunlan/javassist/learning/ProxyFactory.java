package cn.shuaijunlan.javassist.learning;

import java.lang.reflect.InvocationHandler;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 8:06 PM 1/6/19.
 */
public interface ProxyFactory {
    <T> T getProxy(Object target, InvocationHandler handler) throws Throwable;
}
