package cn.shuaijunlan.grpc.learning.streaming;

import cn.shuaijunlan.grpc.leagning.streaming.Average;
import cn.shuaijunlan.grpc.leagning.streaming.Metric;
import cn.shuaijunlan.grpc.leagning.streaming.MetricsServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.stream.Stream;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 10:03 AM 10/26/18.
 */
public class MetricsClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext(true).build();
        MetricsServiceGrpc.MetricsServiceStub serviceStub = MetricsServiceGrpc.newStub(channel);

        StreamObserver<Metric> collect = serviceStub.collect(new StreamObserver<Average>() {
            @Override
            public void onNext(Average average) {
                System.out.println("Average:" + average.getVal());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        });
        Stream.of(1L, 2L, 3L, 4L, 5L, 6L, 7L).map(l -> Metric.newBuilder().setMetric(l).build())
                .forEach(collect::onNext);
        collect.onCompleted();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
