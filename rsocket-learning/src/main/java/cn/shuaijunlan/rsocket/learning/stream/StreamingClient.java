package cn.shuaijunlan.rsocket.learning.stream;


import io.rsocket.AbstractRSocket;
import io.rsocket.ConnectionSetupPayload;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.SocketAcceptor;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.transport.netty.server.TcpServerTransport;
import io.rsocket.util.DefaultPayload;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 7:48 PM 3/15/19.
 */
public class StreamingClient {
    public static void main(String[] args) {


        RSocket socket =
                RSocketFactory.connect()
                        .transport(TcpClientTransport.create("localhost", 7000))
                        .start()
                        .block();

        socket
                .requestStream(DefaultPayload.create("Hello"))
                .map(Payload::getDataUtf8)
                .doOnNext(System.out::println)
                .take(10)
                .then()
                .doFinally(signalType -> socket.dispose())
                .then()
                .block();

    }


}
