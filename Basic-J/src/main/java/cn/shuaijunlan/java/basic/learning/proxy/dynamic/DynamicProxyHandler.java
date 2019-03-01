package cn.shuaijunlan.java.basic.learning.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:36 AM 3/1/19.
 */
public class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
        System.out.println("dynamic proxy handler constuctor: " + proxied.getClass());
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("dynamic proxy name: " + proxy.getClass());
        System.out.println("method: " + method.getName());
        System.out.println("args value: " + Arrays.toString(args));

        List<String> list = new ArrayList<>();
        if (args != null){ //why??
            for (Object arg : args){
                list.add(arg.getClass().getName());
            }
            System.out.println("args data type: " + list.toString());
        }


        Object invokeObject = method.invoke(proxied, args);
        if (invokeObject != null) {
            System.out.println("invoke object: " + invokeObject.getClass());
        } else {
            System.out.println("invoke object is null");
        }
        return invokeObject;
    }
}
