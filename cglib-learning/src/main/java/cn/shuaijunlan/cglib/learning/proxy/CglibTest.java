package cn.shuaijunlan.cglib.learning.proxy;

import net.sf.cglib.proxy.*;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:12 PM 3/6/19.
 *
 * TODO: https://juejin.im/post/5abb4b565188255c9323555a
 */
public class CglibTest {
    @Test
    public void test(){
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[]{hello.class});
        // enhancer.setSuperclass(hello.class);

        CallbackFilter callbackFilter = new CallbackFilter() {

            @Override
            public int accept(Method method) {
                // int flag = 0;
                // if ("getStuName".equals(method.getName())) {
                //     System.err.println("我将此方法过滤掉了，不对该方法进行拦截");
                //     return 1;
                // }
                return 1;
            }
        };

        enhancer.setCallbackFilter(callbackFilter);

        //创建一个回调接口
        Callback interceptor = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                // System.err.println("原方法名是 ： " + method.getName());
                // System.err.println("原方法声明的类为 " + method.getDeclaringClass());
                // // System.err.println("我是 " + (String) methodProxy.invokeSuper(o, objects));
                // System.err.println("我调用结束了");
                assert objects.length != 0;
                return "hello" + objects[0].toString();
            }
        };
        Callback[] callbacks = new Callback[]{interceptor, NoOp.INSTANCE};
        enhancer.setCallbacks(callbacks);

        hello hello = (hello) enhancer.create();
        System.out.println(hello.sayHello("Jun"));
    }

    @Test
    public void test1(){
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[]{hello.class});
        CallbackFilter callbackFilter = new CallbackFilter() {
            @Override
            public int accept(Method method) {
                return 1;
            }
        };
        enhancer.setCallbackFilter(callbackFilter);

        Callback interceptor = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                assert objects.length != 0;
                return "hello" + objects[0].toString();
            }
        };
        Callback[] callbacks = new Callback[]{interceptor, NoOp.INSTANCE};
        enhancer.setCallbacks(callbacks);

        hello hello = (hello) enhancer.create();
        System.out.println(hello.sayHello("Jun"));
    }
}
