package cn.shuaijunlan.netty.http2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http2.Http2SecurityUtil;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.*;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:48 AM 12/6/18.
 */
public class Http2Server {
    // static final boolean SSL = System.getProperty("ssl") != null;
    static final boolean SSL = true;
    static final int PORT = Integer.parseInt(System.getProperty("port", SSL ? "8443" : "8080"));

    public static void main(String[] args) throws CertificateException, SSLException {
       final SslContext sslContext;
       if (SSL){
           SslProvider provider = OpenSsl.isAlpnSupported() ? SslProvider.OPENSSL : SslProvider.JDK;
           SelfSignedCertificate ssc = new SelfSignedCertificate();
           sslContext = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey())
                   .sslProvider(provider)
                   .ciphers(Http2SecurityUtil.CIPHERS, SupportedCipherSuiteFilter.INSTANCE)
                   .applicationProtocolConfig(new ApplicationProtocolConfig(
                           ApplicationProtocolConfig.Protocol.ALPN,
                           ApplicationProtocolConfig.SelectorFailureBehavior.NO_ADVERTISE,
                           ApplicationProtocolConfig.SelectedListenerFailureBehavior.ACCEPT,
                           ApplicationProtocolNames.HTTP_2,
                           ApplicationProtocolNames.HTTP_1_1
                   )).build();
       } else {
           sslContext = null;
       }

        EventLoopGroup group = Epoll.isAvailable() ? new EpollEventLoopGroup() : new NioEventLoopGroup();
       try {
           ServerBootstrap b = new ServerBootstrap();
           b.option(ChannelOption.SO_BACKLOG, 1024)
                   .group(group)
                   .channel(Epoll.isAvailable()? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                   .handler(new LoggingHandler(LogLevel.INFO))
                   .childHandler(new Http2ServerInitializer(sslContext));

           Channel channel = b.bind(PORT).sync().channel();

           System.err.println("Open your HTTP/2-enabled web browser and navigate to " +
                   (SSL? "https" : "http") + "://127.0.0.1:" + PORT + '/');
           channel.closeFuture().sync();
       } catch (InterruptedException e) {
           e.printStackTrace();
       }finally {
           group.shutdownGracefully();
       }
    }
}
