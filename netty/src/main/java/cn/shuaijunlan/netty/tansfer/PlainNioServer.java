package cn.shuaijunlan.netty.tansfer;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 14:19 2018/4/15.
 *
 * 基于Java API实现NIO server
 */
public class PlainNioServer {
    public void server(int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket =serverSocketChannel.socket();
        //将服务器绑定到选定的端口
        InetSocketAddress address = new InetSocketAddress(port);
        serverSocket.bind(address);
        //打开Selector来处理Channel
        Selector selector = Selector.open();
        //将ServerSocket注册到Selector以接受连接
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        final ByteBuffer msg = ByteBuffer.wrap("Hi! \r\n".getBytes());

        for (;;){
            try {
                //等待需要处理的新事件；阻塞将一直持续到下一个传入事件
                selector.select();
            } catch (IOException ex){
                ex.printStackTrace();
                break;
            }
            //获取所有接收事件的SelectorKey实例
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                //检查事件是否是一个新的已经就绪可以被接受的连接
                if (key.isAcceptable()){
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel client = server.accept();
                    client.configureBlocking(false);
                    //接受客户端，并将它注册到选择器
                    client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, msg.duplicate());
                    System.out.println("Accepted connection from " + client);

                }
                //检查套接字是否已经准备好写数据
                if (key.isWritable()){
                    SocketChannel client = (SocketChannel)key.channel();
                    ByteBuffer byteBuffer = (ByteBuffer)key.attachment();
                    while (byteBuffer.hasRemaining()){
                        //将数据写到已连接的客户端
                        if (client.write(byteBuffer) == 0){
                            break;
                        }
                    }
                    //关闭连接
                    client.close();

                }
                key.cancel();
                key.channel().close();
            }
        }

    }
    public static void main(String[] args) {
        System.out.println(SelectionKey.OP_WRITE | SelectionKey.OP_READ);
    }
}
