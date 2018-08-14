package cn.shuaijunlan.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 9:09 PM 2018/08/12.
 */
public class ConsumerProxy {
    public static <T> T consumer(final Class<T> interfaces){
        return (T) Proxy.newProxyInstance(interfaces.getClassLoader(), new Class<?>[]{interfaces}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method.getName()+ ":" + args[0 ]);
                return args[0];
            }
        });

    }

    public static void main(String[] args) {
        IHelloService helloService = ConsumerProxy.consumer(IHelloService.class);
        System.out.println(helloService.sayHello("hello"));
    }
}
