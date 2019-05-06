package cn.shuaijunlan.hessian.learning;

import com.caucho.hessian.client.HessianProxyFactory;

import java.net.MalformedURLException;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 3:51 PM 5/6/19.
 */
public class RemoteingTest {
    public static void main(String[] args) throws MalformedURLException {
        String url = "http://hessian.caucho.com/test/test";

        HessianProxyFactory factory = new HessianProxyFactory();
        BasicAPI basic = (BasicAPI) factory.create(BasicAPI.class, url);

        System.out.println("hello(): " + basic.hello("Junlan"));
    }
}
