package cn.shuaijunlan.java.basic.learning.io.reactor;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 11:47 AM 1/22/19.
 */
public class SocketReadHandler extends AbstractSocketHandler {
    private static Logger logger = Logger.getLogger(SocketReadHandler.class);
    private int block = 4096;
    private ByteBuffer receivable = ByteBuffer.allocate(block);

    SocketReadHandler(ServerDispatcher dispatcher, ServerSocketChannel sc, Selector selector) throws IOException {
        super(dispatcher, sc, selector);
    }

    @Override
    public void runnerExecute(int readyKeyOps) throws IOException {
        int count;
        if (SelectionKey.OP_READ == readyKeyOps) {
            receivable.clear();
            count = socketChannel.read(receivable);
            if (count > 0) {
                logger.debug("Server : Readable.");
                receivable.flip();
                byte[] bytes = new byte[receivable.remaining()];
                receivable.get(bytes);
                String body = new String(bytes, StandardCharsets.UTF_8);
                logger.debug("Server : Receive :" + body);
                socketChannel.register(dispatcher.getWriteSelector(), SelectionKey.OP_WRITE);
            }
        }
    }
}
