package cn.shuaijunlan.hessian.learning;

import com.caucho.hessian.client.HessianProxyFactory;

import java.net.MalformedURLException;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 2:21 PM 11/3/18.
 */
public class GreetingTest {
    public static void main(String[] args) throws MalformedURLException {
        // String url = "http://hessian.caucho.com/test/test";
        //The implement service in testmarkdown module, you can look into.

        String url = "http://localhost:8080/hello";
        HessianProxyFactory factory = new HessianProxyFactory();
        BasicAPI basicAPI = (BasicAPI) factory.create(BasicAPI.class, url);
        System.out.println("hello():" + basicAPI.hello("Junlan"));
    }
}
