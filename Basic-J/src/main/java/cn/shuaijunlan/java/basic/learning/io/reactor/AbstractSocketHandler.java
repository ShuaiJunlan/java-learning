package cn.shuaijunlan.java.basic.learning.io.reactor;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 11:42 AM 1/22/19.
 */
public abstract class AbstractSocketHandler implements Runnable {
    Selector selector;
    SocketChannel socketChannel = null;
    ServerSocketChannel serverSocketChannel;
    ServerDispatcher dispatcher;
    private final static Logger logger = Logger.getLogger(AbstractSocketHandler.class);
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    AbstractSocketHandler(ServerDispatcher dispatcher, ServerSocketChannel sc, Selector selector) throws IOException {
        this.selector = selector;
        this.serverSocketChannel = sc;
        this.dispatcher = dispatcher;
    }

    /**
     * @param readyKeyOps 操作标志位
     * @throws IOException 抛出异常
     */
    public abstract void runnerExecute(int readyKeyOps) throws IOException;

    @Override
    public final void run() {
        while (true) {
            readWriteLock.readLock().lock();
            try {
                int keyOps = this.select();

                runnerExecute(keyOps);

                Thread.sleep(1000);
            } catch (Exception e) {
                try {
                    socketChannel.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                logger.error(e.getMessage());
            } finally {
                readWriteLock.readLock().unlock();
            }
        }
    }

    private int select() throws IOException {
        int keyOps = this.selector.selectNow();

        boolean flag = keyOps > 0;

        if (flag) {
            Set readyKeySet = selector.selectedKeys();
            Iterator iterator = readyKeySet.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = (SelectionKey) iterator.next();
                iterator.remove();

                keyOps = key.readyOps();
                // if (!key.isWritable() && !key.isReadable()){
                //     logger.info("if (!key.isWritable() && !key.isReadable())");
                //     continue;
                // }
                if (keyOps == SelectionKey.OP_READ || keyOps == SelectionKey.OP_WRITE) {
                    socketChannel = (SocketChannel) key.channel();
                    socketChannel.configureBlocking(false);
                    logger.info("keyOps is " + keyOps);
                }
            }
        }
        return keyOps;
    }
}
