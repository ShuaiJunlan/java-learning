package cn.shuaijunlan.java.basic.learning.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 7:12 PM 11/27/18.
 */


public class NioSocketServer extends Thread {
    ServerSocketChannel serverSocketChannel = null;
    Selector selector = null;
    SelectionKey selectionKey = null;

    public void initServer() throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(8000));
        selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    }

    @Override
    public void run() {
        while (true) {
            try {
                int selectKey = selector.select();
                if (selectKey > 0) {
                    Set<SelectionKey> keySet = selector.selectedKeys();
                    Iterator<SelectionKey> iter = keySet.iterator();
                    while (iter.hasNext()) {
                        SelectionKey selectionKey = iter.next();
                        iter.remove();
                        if (selectionKey.isAcceptable()) {
                            accept(selectionKey);
                        }
                        if (selectionKey.isReadable()) {
                            read(selectionKey);
                        }
                        if (selectionKey.isWritable()) {
                            // write(selectionKey);
                            System.out.println();
                        }
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                try {
                    serverSocketChannel.close();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

        }
    }

    public void accept(SelectionKey key) {
        try {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            System.out.println("is acceptable");
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void read(SelectionKey selectionKey) {
        System.out.println("read事件");

        try {
            SocketChannel channel = (SocketChannel) selectionKey.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(100);
            int len = channel.read(byteBuffer);
            if (len > 0) {

                byteBuffer.flip();
                byte[] byteArray = new byte[byteBuffer.limit()];
                byteBuffer.get(byteArray);
                System.out.println("NioSocketServer receive from client:" + new String(byteArray));

            }
            selectionKey.interestOps(SelectionKey.OP_READ);
            selectionKey.interestOps(SelectionKey.OP_READ);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            try {
                serverSocketChannel.close();
                selectionKey.cancel();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    public void write(SelectionKey selectionKey) {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        String httpResponse = "HTTP/1.1 200 OK\r\n" + "Content-Length: 38\r\n" + "Content-Type: text/html\r\n" + "\r\n"
                + "<html><body>Hello World!</body></html>";
        System.out.println("response from server to client");
        try {
            ByteBuffer byteBuffer = ByteBuffer.wrap(httpResponse.getBytes());
            while (byteBuffer.hasRemaining()) {
                socketChannel.write(byteBuffer);
            }
            selectionKey.cancel();
        } catch (IOException e) {
            try {
                selectionKey.cancel();
                serverSocketChannel.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * byte[]转int
     *
     * @param bytes
     * @return
     */
    public static int byteArrayToInt(byte[] bytes) {
        int value = 0;
        // 由高位到低位
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (bytes[i] & 0x000000FF) << shift;// 往高位游
        }
        return value;
    }

    public static void main(String args[]) throws IOException {
        NioSocketServer server = new NioSocketServer();
        server.initServer();
        server.start();

    }
}
