package cn.shuaijunlan.reactor.programming.learning.flux;

import org.junit.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 3:25 PM 4/20/19.
 */
public class FluxBasicTest {
    @Test
    public void test1() {
        Flux<Integer> ints = Flux.range(1, 400)
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) {
                        if (integer <= 100) {
                            return integer;
                        }
                        throw new RuntimeException("Go to 101");
                    }
                });
        ints.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                System.err.println("Error: " + throwable);
            }
        }, new Runnable() {
            @Override
            public void run() {
                System.out.println("Done");
            }
        }, new Consumer<Subscription>() {
            @Override
            public void accept(Subscription subscription) {
                subscription.request(400);
            }
        });


    }
}
