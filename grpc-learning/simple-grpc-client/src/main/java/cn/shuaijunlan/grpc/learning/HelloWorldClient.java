package cn.shuaijunlan.grpc.learning;

import cn.shuaijunlan.grpc.leagning.helloworld.GreeterGrpc;
import cn.shuaijunlan.grpc.leagning.helloworld.HelloReply;
import cn.shuaijunlan.grpc.leagning.helloworld.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


import java.util.concurrent.TimeUnit;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 10:09 PM 10/24/18.
 */
public class HelloWorldClient {
    private final ManagedChannel channel;
    private final GreeterGrpc.GreeterBlockingStub blockingStub;


    public HelloWorldClient(String host,int port){
        channel = ManagedChannelBuilder.forAddress(host,port)
                .usePlaintext(true)
                .build();

        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }


    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public  void greet(String name){
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply response = blockingStub.sayHello(request);


        System.out.println(response.getMessage());

    }

    public static void main(String[] args) throws InterruptedException {
        HelloWorldClient client = new HelloWorldClient("127.0.0.1",50051);
        for(int i=0;i<5;i++){
            client.greet("Junlan"+i);
        }


    }
}
