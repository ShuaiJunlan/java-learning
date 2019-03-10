package cn.shuaijunlan.java.basic.learning.ssl;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 2:41 PM 11/23/18.
 */
public abstract class BaseSSLProvider implements Runnable {
    final SSLEngine engine;
    final Executor ioWorker, taskWorker;
    final ByteBuffer clientWrap, clientUnwrap;
    final ByteBuffer serverWrap, serverUnwrap;

    public BaseSSLProvider(SSLEngine engine, int capacity, Executor ioWorker, Executor taskWorker) {
        this.clientWrap = ByteBuffer.allocate(capacity);
        this.serverWrap = ByteBuffer.allocate(capacity);
        this.clientUnwrap = ByteBuffer.allocate(capacity);
        this.serverUnwrap = ByteBuffer.allocate(capacity);
        this.clientUnwrap.limit(0);
        this.engine = engine;
        this.ioWorker = ioWorker;
        this.taskWorker = taskWorker;

        this.ioWorker.execute(this);
    }

    public abstract void onInput(ByteBuffer decrypted);

    public abstract void onOutput(ByteBuffer encrypted);

    public abstract void onFailure(Exception e);

    public abstract void onSuccess();

    public abstract void onClosed();

    public void sendAsync(final ByteBuffer data) {
        this.ioWorker.execute(new Runnable() {
            @Override
            public void run() {
                clientWrap.put(data);
                BaseSSLProvider.this.run();
            }
        });
    }

    public void notify(final ByteBuffer data) {
        this.ioWorker.execute(new Runnable() {
            @Override
            public void run() {
                clientUnwrap.put(data);
                BaseSSLProvider.this.run();
            }
        });
    }

    @Override
    public void run() {
        while (this.isHandShaking()) {
            continue;
        }
    }

    private synchronized boolean isHandShaking() {
        switch (engine.getHandshakeStatus()) {
            case NOT_HANDSHAKING:
                boolean occupied = false;
            {
                if (clientWrap.position() > 0) {
                    occupied |= this.wrap();
                }
                if (clientUnwrap.position() > 0) {
                    occupied |= this.unwrap();
                }
            }
            return occupied;
            case NEED_WRAP:
                if (!this.wrap()) {
                    return false;
                }
                break;
            case NEED_UNWRAP:
                if (!this.unwrap()) {
                    return false;
                }
                break;
            case NEED_TASK:
                final Runnable sslTask = engine.getDelegatedTask();
                Runnable wrappedTask = () -> {
                    sslTask.run();
                    ioWorker.execute(BaseSSLProvider.this);
                };
                taskWorker.execute(wrappedTask);
                return false;
            case FINISHED:
                throw new IllegalStateException("FINISHED");
            default:
                break;
        }
        return true;
    }


    private boolean wrap() {
        SSLEngineResult wrapResult;

        try {
            clientWrap.flip();
            wrapResult = engine.wrap(clientWrap, serverWrap);
            clientWrap.compact();
        } catch (SSLException exc) {
            this.onFailure(exc);
            return false;
        }

        switch (wrapResult.getStatus()) {
            case OK:
                if (serverWrap.position() > 0) {
                    serverWrap.flip();
                    this.onOutput(serverWrap);
                    serverWrap.compact();
                }
                break;

            case BUFFER_UNDERFLOW:
                // try again later
                break;

            case BUFFER_OVERFLOW:
                throw new IllegalStateException("failed to wrap");

            case CLOSED:
                this.onClosed();
                return false;
        }

        return true;
    }

    private boolean unwrap() {
        SSLEngineResult unwrapResult;

        try {
            clientUnwrap.flip();
            unwrapResult = engine.unwrap(clientUnwrap, serverUnwrap);
            clientUnwrap.compact();
        } catch (SSLException ex) {
            this.onFailure(ex);
            return false;
        }

        switch (unwrapResult.getStatus()) {
            case OK:
                if (serverUnwrap.position() > 0) {
                    serverUnwrap.flip();
                    this.onInput(serverUnwrap);
                    serverUnwrap.compact();
                }
                break;

            case CLOSED:
                this.onClosed();
                return false;

            case BUFFER_OVERFLOW:
                throw new IllegalStateException("failed to unwrap");

            case BUFFER_UNDERFLOW:
                return false;
        }

        if (unwrapResult.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
            this.onSuccess();
            return false;
        }

        return true;
    }
}
