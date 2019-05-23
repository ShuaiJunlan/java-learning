package cn.shuaijunlan.disruptor.learning;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 8:38 PM 5/22/19.
 */
public class Diamond1P3C {

    public static void main(String[] args) {
        ThreadFactory producerFactory = Executors.defaultThreadFactory();

        LongEventFactory eventFactory = new LongEventFactory();

        int ringBufferSize = 1024 * 1024;

        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(eventFactory, ringBufferSize, producerFactory, ProducerType.SINGLE, new BlockingWaitStrategy());

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        //creating 10 consumers
        // LongEventConsumer[] consumers = new LongEventConsumer[10];
        // for (int i = 0; i < 10; i++) {
        //     consumers[i] = new LongEventConsumer();
        // }
        // disruptor.handleEventsWithWorkerPool(consumers);


        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
        BatchEventProcessor<LongEvent> consumer1 = new BatchEventProcessor<>(ringBuffer, sequenceBarrier, new LongEventHandler());

        BatchEventProcessor<LongEvent> consumer2 = new BatchEventProcessor<>(ringBuffer, sequenceBarrier, new LongEventHandler());

        SequenceBarrier consumerBarrier3 = ringBuffer.newBarrier(consumer1.getSequence(), consumer2.getSequence());
        BatchEventProcessor consumer3 = new BatchEventProcessor<>(ringBuffer, consumerBarrier3, new LongEventHandler());

        ringBuffer.addGatingSequences(consumer3.getSequence());

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
