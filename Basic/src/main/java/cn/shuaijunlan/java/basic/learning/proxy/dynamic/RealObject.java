package cn.shuaijunlan.java.basic.learning.proxy.dynamic;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:38 AM 3/1/19.
 */
public class RealObject implements Interface {
    @Override
    public void getMyName() {
        System.out.println("my name is huhx");
    }

    @Override
    public String getNameById(String id) {
        System.out.println("argument id: " + id);
        return "huhx";
    }
}
