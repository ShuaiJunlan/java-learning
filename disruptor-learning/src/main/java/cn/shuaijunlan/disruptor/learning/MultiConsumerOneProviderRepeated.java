package cn.shuaijunlan.disruptor.learning;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 4:06 PM 5/22/19.
 * 
 * multi consumer not repeated consuming
 */
public class MultiConsumerOneProviderRepeated {
    public static void main(String[] args) {
        ThreadFactory producerFactory = Executors.defaultThreadFactory();

        LongEventFactory eventFactory = new LongEventFactory();

        int ringBufferSize = 1024 * 1024;

        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(eventFactory, ringBufferSize, producerFactory, ProducerType.SINGLE, new BlockingWaitStrategy());

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        //creating 10 consumers
        LongEventHandler[] consumers = new LongEventHandler[10];
        for (int i = 0; i < 10; i++) {
            consumers[i] = new LongEventHandler();
        }
        disruptor.handleEventsWith(consumers);

        disruptor.start();

        LongEventProducer longEventProducer = new LongEventProducer(ringBuffer);
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long i = 0; i < 1000; i++){
            bb.putLong(0, i);
            longEventProducer.onData(bb);
        }
        disruptor.shutdown();
    }
}
