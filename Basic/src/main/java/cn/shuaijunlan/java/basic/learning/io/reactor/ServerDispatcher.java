package cn.shuaijunlan.java.basic.learning.io.reactor;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 11:49 AM 1/22/19.
 */
public class ServerDispatcher {
    private ServerSocketChannel ssc;
    private Selector[] selectors = new Selector[3];
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(5);

    private SelectorProvider selectorProvider;

    private final static Logger logger = Logger.getLogger(ServerDispatcher.class);

    public ServerDispatcher(ServerSocketChannel ssc, SelectorProvider selectorProvider) throws IOException {
        this.ssc = ssc;
        this.selectorProvider = selectorProvider;
        for (int i = 0; i < 3; i++) {
            selectors[i] = selectorProvider.openSelector();
        }
    }

    public Selector getAcceptSelector() {
        return selectors[0];
    }

    public Selector getReadSelector() {
        return selectors[1];
    }

    public Selector getWriteSelector() {
        return selectors[2];
    }

    public void execute() throws IOException {
        AbstractSocketHandler r = new SocketAcceptHandler(this, ssc, getAcceptSelector());
        EXECUTOR_SERVICE.execute(r);

        r = new SocketReadHandler(this, ssc, getReadSelector());
        EXECUTOR_SERVICE.execute(r);

        r = new SocketWriteHandler(this, ssc, getWriteSelector());
        EXECUTOR_SERVICE.execute(r);
    }

}
