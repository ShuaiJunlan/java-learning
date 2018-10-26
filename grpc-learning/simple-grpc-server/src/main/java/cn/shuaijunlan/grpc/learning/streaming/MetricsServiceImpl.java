package cn.shuaijunlan.grpc.learning.streaming;

import cn.shuaijunlan.grpc.leagning.streaming.Average;
import cn.shuaijunlan.grpc.leagning.streaming.Metric;
import cn.shuaijunlan.grpc.leagning.streaming.MetricsServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 9:50 AM 10/26/18.
 */
public class MetricsServiceImpl extends MetricsServiceGrpc.MetricsServiceImplBase {
    @Override
    public StreamObserver<Metric> collect(StreamObserver<Average> responseObserver) {
        return new StreamObserver<Metric>() {
            private long sum = 0;
            private long count  = 0;
            @Override
            public void onNext(Metric metric) {
                System.out.println("value:" + metric);
                sum += metric.getMetric();
                count++;
            }

            @Override
            public void onError(Throwable throwable) {
                responseObserver.onError(throwable);
            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(Average.newBuilder()
                .setVal((float)sum / count)
                .build());
            }
        };
    }
}
