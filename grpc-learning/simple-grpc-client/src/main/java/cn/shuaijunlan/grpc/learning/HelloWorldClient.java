package cn.shuaijunlan.grpc.learning;

import cn.shuaijunlan.grpc.leagning.helloworld.GreeterGrpc;
import cn.shuaijunlan.grpc.leagning.helloworld.HelloReply;
import cn.shuaijunlan.grpc.leagning.helloworld.HelloRequest;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;


import java.util.concurrent.*;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 10:09 PM 10/24/18.
 */
public class HelloWorldClient {
    private final ManagedChannel channel;
    private final GreeterGrpc.GreeterBlockingStub blockingStub;
    private final GreeterGrpc.GreeterStub asyncStub;
    private final GreeterGrpc.GreeterFutureStub futureStub;

    private final CountDownLatch latch = new CountDownLatch(1);
    private static CyclicBarrier barrier = new CyclicBarrier(2);



    public HelloWorldClient(String host,int port){
        channel = ManagedChannelBuilder.forAddress(host,port)
                .usePlaintext(true)
                .build();

        blockingStub = GreeterGrpc.newBlockingStub(channel);

        asyncStub = GreeterGrpc.newStub(channel);

        futureStub = GreeterGrpc.newFutureStub(channel);
    }


    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public  void greetBlocking(String name){
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply response = blockingStub.sayHello(request);


        System.out.println("Blocking: " + response.getMessage());

    }

    public void greetAsync(String name){
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        StreamObserver<HelloReply> streamObserver = new StreamObserver<HelloReply>() {
            @Override
            public void onNext(HelloReply helloReply) {
                System.out.println("Async: " + helloReply.getMessage());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("error: " + throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Completed");
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                // try {
                //     shutdown();
                // }catch (InterruptedException e){
                //     e.printStackTrace();
                // }
            }
        };
        asyncStub.sayHello(request, streamObserver);
    }

    public void greetFuture(String name){
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        try {
            ListenableFuture<HelloReply> listenableFuture = futureStub.sayHello(request);
            HelloReply helloReply = listenableFuture.get();
            System.out.println("Future: " + helloReply.getMessage());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        HelloWorldClient client = new HelloWorldClient("127.0.0.1",50051);
        for(int i=0;i<5;i++){
            client.greetBlocking("Junlan"+i);
            client.greetAsync("Junlan"+i);
            barrier.await();
            barrier.reset();
            client.greetFuture("Junlan" + i);
        }
        Thread.sleep(10000);
    }
}
