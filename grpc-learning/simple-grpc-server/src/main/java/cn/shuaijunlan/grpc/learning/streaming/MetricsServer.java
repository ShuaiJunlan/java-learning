package cn.shuaijunlan.grpc.learning.streaming;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 9:55 AM 10/26/18.
 */
public class MetricsServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8080).addService(new MetricsServiceImpl()).build();

        server.start();
        server.awaitTermination();
    }
}
