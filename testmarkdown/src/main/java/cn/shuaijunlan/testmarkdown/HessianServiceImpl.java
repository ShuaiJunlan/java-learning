package cn.shuaijunlan.testmarkdown;

import cn.shuaijunlan.hessian.learning.BasicAPI;
import com.caucho.hessian.server.HessianServlet;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 2:57 PM 11/3/18.
 */
public class HessianServiceImpl extends HessianServlet implements BasicAPI {
    @Override
    public String hello() {
        return "hello world!";
    }
}
