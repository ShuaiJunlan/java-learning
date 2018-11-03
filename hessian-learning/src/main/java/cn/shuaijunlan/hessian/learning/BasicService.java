package cn.shuaijunlan.hessian.learning;

import com.caucho.hessian.server.HessianServlet;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 2:18 PM 11/3/18.
 */
public class BasicService extends HessianServlet implements  BasicAPI {
    private String _greeting = "Hello, world";

    public void setGreeting(String greeting){
        this._greeting = greeting;
    }

    @Override
    public String hello() {
        return _greeting;
    }
}
