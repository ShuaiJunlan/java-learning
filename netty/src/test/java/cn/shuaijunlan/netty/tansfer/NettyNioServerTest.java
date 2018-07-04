package cn.shuaijunlan.netty.tansfer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 21:16 2018/4/28.
 */
public class NettyNioServerTest {

    @Test
    public void server() throws InterruptedException {
        NettyNioServer nettyNioServer = new NettyNioServer();
        nettyNioServer.server(9999);
    }
}