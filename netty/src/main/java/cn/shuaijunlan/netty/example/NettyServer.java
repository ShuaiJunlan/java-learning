package cn.shuaijunlan.netty.example;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 13:44 2018/5/11.
 */
public class NettyServer {
    public void start(Integer port) throws InterruptedException {
        EventLoopGroup bossGroup = Epoll.isAvailable() ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        EventLoopGroup workGroup = Epoll.isAvailable() ? new EpollEventLoopGroup(4) : new NioEventLoopGroup(4);

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workGroup).channel(Epoll.isAvailable() ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                .localAddress(port)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new ObjectDecoder(1024*1024,
                                        ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())) )
                                .addLast(new ObjectEncoder())
                                .addLast(new SimpleChannelInboundHandler<Object>() {
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        System.out.println("Receive message:" + msg);
                                        // send ten times msg
                                        for(int i = 0; i < 10; i++){
                                            ctx.writeAndFlush("Hello " + msg);
                                        }
                                    }
                                });
                    }
                });
        ChannelFuture channelFuture = bootstrap.bind().sync();
        channelFuture.channel().closeFuture().sync();

        bossGroup.shutdownGracefully().sync();
        workGroup.shutdownGracefully().sync();
    }

    public static void main(String[] args) {
        try {
            NettyServer server = new NettyServer();
            server.start(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
