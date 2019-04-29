package cn.shuaijunlan.rsocket.learning.stream;

import io.rsocket.AbstractRSocket;
import io.rsocket.ConnectionSetupPayload;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.SocketAcceptor;
import io.rsocket.transport.netty.server.TcpServerTransport;
import io.rsocket.util.DefaultPayload;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.Duration;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 3:22 PM 4/10/19.
 */
public class StreamingServer {
    public static void main(String[] args) throws IOException {
        RSocketFactory.receive()
                .acceptor(new StreamingServer.SocketAcceptorImpl())
                .transport(TcpServerTransport.create("localhost", 7000))
                .start()
                .subscribe();

        System.in.read();


    }

    private static class SocketAcceptorImpl implements SocketAcceptor {
        @Override
        public Mono<RSocket> accept(ConnectionSetupPayload setupPayload, RSocket reactiveSocket) {
            return Mono.just(
                    new AbstractRSocket() {
                        @Override
                        public Flux<Payload> requestStream(Payload payload) {
                            return Flux.interval(Duration.ofMillis(100))
                                    .map(aLong -> DefaultPayload.create("Interval: " + aLong));
                        }
                    });
        }
    }
}
