package cn.shuaijunlan.netty.example;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 13:53 2018/5/11.
 */
public class NettyClient {
    public Channel channel;
    public void start(String host, Integer port) throws InterruptedException {
    EventLoopGroup workGroup = Epoll.isAvailable() ? new EpollEventLoopGroup(4) : new NioEventLoopGroup(4);
    Bootstrap bootstrap = new Bootstrap();
    bootstrap.group(workGroup)
            .channel(Epoll.isAvailable() ? EpollSocketChannel.class : NioSocketChannel.class)
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    // Using Java Object serializable, you can also use other serializable frameworks like thrift, Protobuf and so on.
                    ch.pipeline()
                            .addLast(new ObjectDecoder(1024*1024,
                                    ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())) )
                            .addLast(new ObjectEncoder())
                            .addLast(new SimpleChannelInboundHandler<Object>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    System.out.println("Receive msg: " + msg);
                                }
                            });
                }
            });
    // Connect to the server sync
    channel = bootstrap.connect(host, port).sync().channel();
    }

    public static void main(String[] args) {
        try {
            NettyClient nettyClient = new NettyClient();
            nettyClient.start("127.0.0.1", 20000);
            if (nettyClient.channel != null && nettyClient.channel.isActive()){
                System.out.println("Send message to server");
                nettyClient.channel.writeAndFlush("Junlan").addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if (channelFuture.isSuccess()){
                            System.out.println("Msg send Successfully");
///                            nettyClient.channel.close();
                        }

                    }
                });
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
