package cn.shuaijunlan.java.basic.learning.io.reactor;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 11:48 AM 1/22/19.
 */
public class SocketWriteHandler extends AbstractSocketHandler {
    private static Logger logger = Logger.getLogger(SocketWriteHandler.class);
    private int block = 4096;
    private ByteBuffer sendBuffer = ByteBuffer.allocate(block);
    private static int index = 1;

    SocketWriteHandler(ServerDispatcher dispatcher, ServerSocketChannel sc, Selector selector) throws IOException {
        super(dispatcher, sc, selector);
    }

    @Override
    public void runnerExecute(int readyKeyOps) throws IOException {
        if (readyKeyOps == SelectionKey.OP_WRITE) {
            logger.debug("Server : Writable.");
            String data = String.format("%d", index);
            byte[] req = data.getBytes();
            sendBuffer.clear();

            logger.debug(String.format("Server : Write %s", data));

            sendBuffer = ByteBuffer.allocate(req.length);
            sendBuffer.put(req);
            sendBuffer.flip();
            socketChannel.write(sendBuffer);
            index++;
            socketChannel.register(dispatcher.getReadSelector(), SelectionKey.OP_READ);
        }
    }
}
