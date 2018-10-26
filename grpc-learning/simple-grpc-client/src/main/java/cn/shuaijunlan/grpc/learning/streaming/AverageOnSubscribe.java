package cn.shuaijunlan.grpc.learning.streaming;

import cn.shuaijunlan.grpc.leagning.streaming.Average;
import cn.shuaijunlan.grpc.leagning.streaming.Metric;
import cn.shuaijunlan.grpc.leagning.streaming.MetricsServiceGrpc;
import io.grpc.stub.StreamObserver;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 9:57 AM 10/26/18.
 */
public class AverageOnSubscribe implements Observable.OnSubscribe<Average> {
    private final MetricsServiceGrpc.MetricsServiceStub serviceStub;
    private final Observable<Metric> metricObservable;

    public AverageOnSubscribe(Observable<Metric> metricObservable, MetricsServiceGrpc.MetricsServiceStub serviceStub){
        this.serviceStub = serviceStub;
        this.metricObservable = metricObservable;
    }
    @Override
    public void call(Subscriber<? super Average> subscriber) {
        final AtomicBoolean started = new AtomicBoolean(false);
        StreamObserver<Metric> toServer = serviceStub.collect(new StreamObserver<Average>() {
            @Override
            public void onNext(Average average) {
                if (started.compareAndSet(false, true)){
                    subscriber.onStart();
                }
                subscriber.onNext(average);
            }

            @Override
            public void onError(Throwable throwable) {
                subscriber.onError(throwable);
            }

            @Override
            public void onCompleted() {
                subscriber.onCompleted();
            }
        });
        metricObservable.forEach(toServer :: onNext);
        toServer.onCompleted();
    }
}
