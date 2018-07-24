package cn.shuaijunlan.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledByteBufAllocator;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 4:48 PM 2018/07/24.
 */
public class ByteBufTest {
    public static void main(String[] args) {
        ByteBufAllocator pooledByteBuf = new UnpooledByteBufAllocator(true);

    }
}
