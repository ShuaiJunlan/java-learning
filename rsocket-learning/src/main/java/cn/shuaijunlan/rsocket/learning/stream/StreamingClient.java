package cn.shuaijunlan.rsocket.learning.stream;


import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;

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
