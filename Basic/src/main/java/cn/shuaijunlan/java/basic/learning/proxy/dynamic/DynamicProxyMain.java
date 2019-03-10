package cn.shuaijunlan.java.basic.learning.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:37 AM 3/1/19.
 */
public class DynamicProxyMain {
    public static void consumer(Interface iface) {
        iface.getMyName();
        String name = iface.getNameById("1");
        System.out.println("name: " + name);
    }

    public static void main(String[] args) throws Exception, SecurityException, Throwable {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        RealObject realObject = new RealObject();
        consumer(realObject);
        System.out.println("==============================");

        // 动态代理
        ClassLoader classLoader = Interface.class.getClassLoader();
        Class<?>[] interfaces = new Class[] { Interface.class };
        InvocationHandler handler = new DynamicProxyHandler(realObject);
        Interface proxy = (Interface) Proxy.newProxyInstance(classLoader, interfaces, handler);

        System.out.println("in dynamicproxyMain proxy: " + proxy.getClass());
        consumer(proxy);
    }
}