package cn.shuaijunlan.grpc.learning.streaming;

import cn.shuaijunlan.grpc.leagning.streaming.Average;
import cn.shuaijunlan.grpc.leagning.streaming.Metric;
import cn.shuaijunlan.grpc.leagning.streaming.MetricsServiceGrpc;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import rx.Observable;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 10:08 AM 10/26/18.
 */
public class MetricsRxClient {
    private final Channel channel;
    private final MetricsServiceGrpc.MetricsServiceStub serviceStub;

    public MetricsRxClient(Channel channel) {
        this.channel = channel;
        this.serviceStub = MetricsServiceGrpc.newStub(channel);
    }

    public Observable<Average> collect(Observable<Metric> metricObservable) {
        return Observable.create(new AverageOnSubscribe(metricObservable, serviceStub));
    }

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext(true).build();

        rx.Observable<Long> metrics = rx.Observable.from(new Long[]{1L, 2L, 3L, 4L, 5L, 6L, 7L});
        MetricsRxClient client = new MetricsRxClient(channel);

        // client.collect(metrics
        //         .map(l -> Metric.newBuilder().setMetric(l).build()))
        //         .subscribe(avg -> {
        //             System.out.println(avg);
        //         });

        client.collect(metrics
                .map(l -> Metric.newBuilder().setMetric(l).build()))
                .subscribe(average -> {
                    System.out.println(average);
                });
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
