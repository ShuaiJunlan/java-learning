package cn.shuaijunlan.java.basic.learning.io.reactor;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 11:46 AM 1/22/19.
 */
public class SocketAcceptHandler extends AbstractSocketHandler {
    private static final Logger logger = Logger.getLogger(SocketAcceptHandler.class);

    SocketAcceptHandler(ServerDispatcher dispatcher, ServerSocketChannel sc, Selector selector) throws IOException {
        super(dispatcher, sc, selector);
        serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT, this);
    }

    @Override
    public void runnerExecute(int readyKeyOps) throws IOException {
        if (readyKeyOps == SelectionKey.OP_ACCEPT) {
            socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            logger.debug("Server accept");

            socketChannel.register(dispatcher.getReadSelector(), SelectionKey.OP_READ);
        }
    }
}
