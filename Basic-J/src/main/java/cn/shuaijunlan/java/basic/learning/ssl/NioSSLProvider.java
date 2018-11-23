package cn.shuaijunlan.java.basic.learning.ssl;

import javax.net.ssl.SSLEngine;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.WritableByteChannel;
import java.util.concurrent.Executor;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 2:41 PM 11/23/18.
 */
public abstract class NioSSLProvider extends SSLProvider {
    private final ByteBuffer buffer = ByteBuffer.allocate(32 * 1024);
    private final SelectionKey key;
    public NioSSLProvider(SelectionKey key, SSLEngine engine, int bufferSize, Executor ioWorker, Executor taskWorker){
        super(engine, bufferSize, ioWorker, taskWorker);
        this.key = key;
    }

    @Override
    public void onOutput(ByteBuffer encrypted) {
        try {
            ((WritableByteChannel)this.key.channel()).write(encrypted);
        }catch (IOException ex){
            throw new IllegalStateException(ex);
        }
    }

    public boolean processInput(){
        buffer.clear();
        int bytes;
        try {
            bytes = ((ReadableByteChannel)this.key.channel()).read(buffer);
        } catch (IOException e) {
            bytes = -1;
        }
        if (bytes == -1){
            return false;
        }
        buffer.flip();
        ByteBuffer copy = ByteBuffer.allocate(bytes);
        copy.put(buffer);
        copy.flip();
        this.notify(copy);
        return true;
    }

}
