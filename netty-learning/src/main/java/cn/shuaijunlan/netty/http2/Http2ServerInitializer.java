package cn.shuaijunlan.netty.http2;

import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpServerUpgradeHandler;
import io.netty.handler.codec.http2.CleartextHttp2ServerUpgradeHandler;
import io.netty.handler.codec.http2.Http2CodecUtil;
import io.netty.handler.codec.http2.Http2ServerUpgradeCodec;
import io.netty.handler.ssl.SslContext;
import io.netty.util.AsciiString;
import io.netty.util.ReferenceCountUtil;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 10:03 AM 12/6/18.
 */
public class Http2ServerInitializer extends ChannelInitializer<SocketChannel> {
    private static final HttpServerUpgradeHandler.UpgradeCodecFactory upgradeCodecFactory = new HttpServerUpgradeHandler.UpgradeCodecFactory() {
        @Override
        public HttpServerUpgradeHandler.UpgradeCodec newUpgradeCodec(CharSequence protocol) {
            if (AsciiString.contentEquals(Http2CodecUtil.HTTP_UPGRADE_PROTOCOL_NAME, protocol)) {
                return new Http2ServerUpgradeCodec(new HelloWorldHttp2HandlerBuilder().build());
            } else {
                return null;
            }
        }
    };
    private final SslContext sslContext;
    private final int maxHttpContentLength;

    public Http2ServerInitializer(SslContext sslContext){
        this(sslContext, 16 * 1024);
    }
    public Http2ServerInitializer(SslContext sslContext, int maxHttpContentLength){
        if (maxHttpContentLength < 0){
            throw new IllegalArgumentException("maxHttpContentLength (expected >= 0): " + maxHttpContentLength);
        }
        this.sslContext = sslContext;
        this.maxHttpContentLength = maxHttpContentLength;
    }
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        if (sslContext != null){
            configureSsl(ch);
        }else {
            configureClearText(ch);
        }
    }

    private void configureSsl(SocketChannel channel){
        channel.pipeline().addLast(sslContext.newHandler(channel.alloc()), new Http2OrHttpHandler());
    }
    private void configureClearText(SocketChannel channel){
        final ChannelPipeline p = channel.pipeline();
        final HttpServerCodec sourceCodec = new HttpServerCodec();
        final HttpServerUpgradeHandler upgradeHandler = new HttpServerUpgradeHandler(sourceCodec, upgradeCodecFactory);
        final CleartextHttp2ServerUpgradeHandler cleartextHttp2ServerUpgradeHandler =
                new CleartextHttp2ServerUpgradeHandler(sourceCodec, upgradeHandler,
                        new HelloWorldHttp2HandlerBuilder().build());

        p.addLast(cleartextHttp2ServerUpgradeHandler);
        p.addLast(new SimpleChannelInboundHandler<HttpMessage>() {
            @Override
            protected void channelRead0(ChannelHandlerContext ctx, HttpMessage msg) throws Exception {
                // If this handler is hit then no upgrade has been attempted and the client is just talking HTTP.
                System.err.println("Directly talking: " + msg.protocolVersion() + " (no upgrade was attempted)");
                ChannelPipeline pipeline = ctx.pipeline();
                ChannelHandlerContext thisCtx = pipeline.context(this);
                pipeline.addAfter(thisCtx.name(), null, new HelloWorldHttp1Handler("Direct. No Upgrade Attempted."));
                pipeline.replace(this, null, new HttpObjectAggregator(maxHttpContentLength));
                ctx.fireChannelRead(ReferenceCountUtil.retain(msg));
            }
        });

        p.addLast(new UserEventLogger());
    }


    /**
     * Class that logs any User Events triggered on this channel.
     */
    private static class UserEventLogger extends ChannelInboundHandlerAdapter {
        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
            System.out.println("User Event Triggered: " + evt);
            ctx.fireUserEventTriggered(evt);
        }
    }
}
