package cn.shuaijunlan.netty.unpack.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 9:27 AM 10/29/18.
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ":客户端写数据");

        // for (int i = 0; i < 1; i++) {
        //     ByteBuf buffer = getByteBuf(ctx);
        //     ctx.channel().writeAndFlush(buffer);
        // }
        ctx.channel().close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(new Date() + "客户端收到数据-> " + buf.toString(Charset.forName("utf-8")));
    }

    private ByteBuf getByteBuf(ChannelHandlerContext context){
        byte[] bytes = "Hello, Mr shuai".getBytes(Charset.forName("utf-8"));

        ByteBuf buffer = context.alloc().buffer();
        buffer.writeBytes(bytes);
        return buffer;
    }
}
