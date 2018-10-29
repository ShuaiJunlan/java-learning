package cn.shuaijunlan.netty.unpack.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 9:27 AM 10/29/18.
 */
public class NettyClient {
    private static final EventLoopGroup worker = Epoll.isAvailable() ? new EpollEventLoopGroup(): new NioEventLoopGroup();

    private static final String host = "127.0.0.1";
    public static final int port = 1234;
    private static final int MAX_RETRY = 5;

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(worker)
                .channel(Epoll.isAvailable() ? EpollSocketChannel.class : NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().
                                addLast(new ClientHandlerLifeCycleTest()).
                                addLast(new FirstClientHandler());
                    }
                });
        connect(bootstrap, host, port, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry){
        ChannelFuture channelFuture = bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()){
                System.out.println("连接成功！");
            }else if (retry == 0){
                System.out.println("连接次数已经用完，连接失败！");
            }else{
                int order = (MAX_RETRY - retry) + 1;

                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry), delay, TimeUnit.SECONDS);
            }

        });
    }

}
