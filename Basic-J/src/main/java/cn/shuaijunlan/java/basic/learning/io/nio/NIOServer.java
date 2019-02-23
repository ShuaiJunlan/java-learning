package cn.shuaijunlan.java.basic.learning.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 10:12 AM 11/26/18.
 *
 * TODO: nio的客户端如果关闭了，服务端还是会收到该channel的读事件，但是数目为0，而且会读到-1，
 *  其实-1在网络io中就是socket关闭的含义，在文件时末尾的含义，
 *  所以为了避免客户端关闭服务端一直收到读事件，
 *  必须检测上一次的读是不是-1，如果是-1，就关闭这个channel。
 */
public class NIOServer {
    private static AtomicLong atomicLong = new AtomicLong(0);
    public static void main(String[] args) throws IOException {
        Selector serverSelector = Selector.open();
        Selector clientSelector = Selector.open();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //init
        ServerSocketChannel listenChannel = ServerSocketChannel.open();
        listenChannel.configureBlocking(false);

        //register
        executorService.execute(() -> {
            try {
                listenChannel.register(serverSelector, SelectionKey.OP_ACCEPT);

                //binding
                listenChannel.socket().bind(new InetSocketAddress(8000));
                while (true){
                    if (serverSelector.select(1) > 0){
                        Set<SelectionKey> set = serverSelector.selectedKeys();
                        System.out.println("serverSelector size:" + set.size());
                        Iterator<SelectionKey> keyIterator = set.iterator();
                        while (keyIterator.hasNext()){
                            SelectionKey k = keyIterator.next();
                            if (k.isAcceptable()){
                                try {
                                    SocketChannel clientChannel = ((ServerSocketChannel) k.channel()).accept();
                                    clientChannel.configureBlocking(false);
                                    clientChannel.register(clientSelector, SelectionKey.OP_READ);
                                }finally {
                                    keyIterator.remove();
                                }
                            }
                        }
                    }
                }
            } catch (ClosedChannelException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(() -> {
            try {
                while (true){
                    if (clientSelector.select(1) > 0){
                        Set<SelectionKey> set = clientSelector.selectedKeys();
                        System.out.println("clientSelector size:" + set.size());
                        Iterator<SelectionKey> keyIterator = set.iterator();
                        while (keyIterator.hasNext()){
                            SelectionKey key = keyIterator.next();
                            if (key.isReadable()){
                                try {
                                    SocketChannel clientChannel = (SocketChannel) key.channel();
                                    // ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                    // 解决粘包问题
                                    ByteBuffer byteBuffer = ByteBuffer.allocate(11);

                                    int c = clientChannel.read(byteBuffer);
                                    if (c == -1){
                                        System.out.println("client disconnect!!");
                                        clientChannel.close();
                                        key.cancel();
                                        continue;
                                    }
                                    byteBuffer.flip();
                                    System.out.println(atomicLong.incrementAndGet() + Charset.defaultCharset().newDecoder().decode(byteBuffer).toString());
                                    if (atomicLong.get() == 2006){
                                        System.exit(1);
                                    }
                                }finally {
                                    if (key.isValid()){
                                        key.interestOps(SelectionKey.OP_READ);
                                    }
                                    keyIterator.remove();
                                }
                            }
                        }
                    }
                }
            } catch (CharacterCodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }
}
