package cn.shuaijunlan.java.basic.learning.ssl;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 2:32 PM 11/23/18.
 */
public class NioSSLExample {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        InetSocketAddress address = new InetSocketAddress("www.amazon.com", 443);
        SelectionKey key = null;
        try {
            Selector selector = Selector.open();
            SocketChannel channel = SocketChannel.open();
            channel.connect(address);
            channel.configureBlocking(false);
            int ops = SelectionKey.OP_CONNECT | SelectionKey.OP_READ;
            key = channel.register(selector, ops);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Executor ioWorker = Executors.newSingleThreadExecutor();
        final Executor taskWorker = Executors.newFixedThreadPool(2);

        final SSLEngine engine = SSLContext.getDefault().createSSLEngine();
        engine.setUseClientMode(true);
        engine.beginHandshake();
        final int ioBufferSize = 32 * 1024;
        final NioSSLProvider ssl = new NioSSLProvider(key, engine, ioBufferSize, ioWorker, taskWorker) {
            @Override
            public void onInput(ByteBuffer decrypted) {
                //http response
                byte[] dst = new byte[decrypted.remaining()];
                decrypted.get(dst);
                String response = new String(dst);
                System.out.println(response);
                System.out.flush();
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("handshake failure");
                e.printStackTrace();
            }

            @Override
            public void onSuccess() {
                System.out.println("handshake success");
                SSLSession session = engine.getSession();
                try {
                    System.out.println("local principal: " + session.getLocalPrincipal());
                    System.out.println("remote principal: " + session.getPeerPrincipal());
                    System.out.println("cipher: " + session.getCipherSuite());
                }catch (Exception e){
                    e.printStackTrace();
                }

                //Http request
                StringBuilder http = new StringBuilder();
                http.append("GET / HTTP/1.0\r\n");
                http.append("Connection: close\r\n");
                http.append("\r\n");

                byte[] data = http.toString().getBytes();
                ByteBuffer send = ByteBuffer.wrap(data);
                this.sendAsync(send);
            }

            @Override
            public void onClosed() {
                System.out.println("ssl session closed");
            }
        };
        // nio selector
        while (true){
            key.selector().select();
            Iterator<SelectionKey> keyIterator = key.selector().selectedKeys().iterator();
            while (keyIterator.hasNext()){
                keyIterator.next();
                keyIterator.remove();
                ssl.processInput();
            }
        }
    }
}
