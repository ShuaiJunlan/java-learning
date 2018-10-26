package cn.shuaijunlan.grpc.learning.helloworld;

import cn.shuaijunlan.grpc.leagning.helloworld.GreeterGrpc;
import cn.shuaijunlan.grpc.leagning.helloworld.HelloReply;
import cn.shuaijunlan.grpc.leagning.helloworld.HelloRequest;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 10:07 PM 10/24/18.
 */

public class HelloWorldServer {
    private static final int PORT = 50051;
    private Server server;

    private void start() throws IOException {
        server = ServerBuilder.forPort(PORT)
                .addService(new GreeterImpl())
                .build()
                .start();

        System.out.println("service start...");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            HelloWorldServer.this.stop();
            System.err.println("*** server shut down");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    // block 一直到退出程序
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {

        final HelloWorldServer server = new HelloWorldServer();
        server.start();
        server.blockUntilShutdown();
    }


    // 实现 定义一个实现服务接口的类
    private class GreeterImpl extends GreeterGrpc.GreeterImplBase {

        @Override
        public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
            System.out.println("service:"+req.getName());
            HelloReply reply = HelloReply.newBuilder().setMessage(("Hello: " + req.getName())).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

        // @Override
        // public void sayHello

        @Override
        public void sayHelloAgain(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
            // super.sayHelloAgain(request, responseObserver);
            System.out.println("Say hello again:" + request.getName());
            HelloReply reply = HelloReply.newBuilder().setMessage(("Hello Again: " + request.getName())).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}
