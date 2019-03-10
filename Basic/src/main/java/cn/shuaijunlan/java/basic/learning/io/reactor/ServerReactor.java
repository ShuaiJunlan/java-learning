package cn.shuaijunlan.java.basic.learning.io.reactor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 11:50 AM 1/22/19.
 */
public class ServerReactor implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(ServerReactor.class);
    private ServerSocketChannel serverSocketChannel;

    ServerReactor(int port) throws IOException {
        SelectorProvider selectorProvider = SelectorProvider.provider();

        //ServerSocketChannel.open()
        serverSocketChannel = selectorProvider.openServerSocketChannel();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress("localhost", port), 1024);
        serverSocketChannel.configureBlocking(false);
        logger.debug(String.format("Server : Server Start.----%d", port));
    }

    @Override
    public void run() {
        try {
            new ServerDispatcher(serverSocketChannel, SelectorProvider.provider()).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
