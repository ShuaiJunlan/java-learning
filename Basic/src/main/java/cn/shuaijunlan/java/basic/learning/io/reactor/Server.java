package cn.shuaijunlan.java.basic.learning.io.reactor;

import java.io.IOException;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 11:51 AM 1/22/19.
 * <p>
 * TODO: https://blog.csdn.net/jjzhk/article/details/39553613
 */
public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        int port = 8008;
        new Thread(new ServerReactor(port)).start();
    }
}
