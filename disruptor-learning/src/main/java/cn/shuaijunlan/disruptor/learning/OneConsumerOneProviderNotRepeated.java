package cn.shuaijunlan.disruptor.learning;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 8:28 PM 5/21/19.
 */
public class OneConsumerOneProviderNotRepeated {
    public static void main(String[] args) throws InterruptedException {
        LongEventFactory factory = new LongEventFactory();

        int bufferSize = 1024;

        Disruptor<LongEvent> disruptor = new Disruptor<>(factory, bufferSize, DaemonThreadFactory.INSTANCE);

        // Disruptor<LongEvent> disruptor = new Disruptor<>(
        //         factory, bufferSize, ProducerType.SINGLE, new BlockingWaitStrategy(), DaemonThreadFactory.INSTANCE);

        disruptor.handleEventsWith(new LongEventHandler());

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long i = 0; i < 10000; i++) {
            bb.putLong(0, i);
            producer.onData(bb);
        }
        Thread.sleep(1000);
    }
}
