package cn.shuaijunlan.javassist.learning;



import java.lang.reflect.InvocationHandler;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 8:07 PM 1/6/19.
 */
public class JavassistProxyFactory implements ProxyFactory {
    @Override
    public <T> T getProxy(Object target, InvocationHandler handler) throws Throwable {
        return (T) ProxyGenerator.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass(), handler);
    }
}
