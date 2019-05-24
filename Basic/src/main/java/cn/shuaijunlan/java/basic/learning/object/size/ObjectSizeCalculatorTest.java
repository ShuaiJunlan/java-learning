package cn.shuaijunlan.java.basic.learning.object.size;

import com.lmax.disruptor.Sequence;
import com.sun.jmx.snmp.Timestamp;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import org.junit.Test;

import java.util.Date;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 11:08 AM 5/24/19.
 */
public class ObjectSizeCalculatorTest {
    @Test
    public void test1(){
        System.out.println("Object: " + ObjectSizeCalculator.getObjectSize(new Object()));
        System.out.println("Integer: " + ObjectSizeCalculator.getObjectSize(Integer.valueOf(122)));
        System.out.println("Long: " + ObjectSizeCalculator.getObjectSize(Long.valueOf(122L)));
        System.out.println("Double: " + ObjectSizeCalculator.getObjectSize(Double.valueOf(122.22)));
        System.out.println("Float: " + ObjectSizeCalculator.getObjectSize(Float.valueOf(122.22f)));
        System.out.println("Boolean: " + ObjectSizeCalculator.getObjectSize(Boolean.valueOf(false)));
        System.out.println("Character: " + ObjectSizeCalculator.getObjectSize(Character.valueOf('a')));
        System.out.println("Short: " + ObjectSizeCalculator.getObjectSize(Short.valueOf("1")));
        System.out.println("Byte: " + ObjectSizeCalculator.getObjectSize(Byte.valueOf("1")));
        System.out.println("Date: " + ObjectSizeCalculator.getObjectSize(new Date()));
        System.out.println("Timestamp: " + ObjectSizeCalculator.getObjectSize(new Timestamp(System.currentTimeMillis())));

        System.out.println(ObjectSizeCalculator.getObjectSize(new LongObject()));
        System.out.println(ObjectSizeCalculator.getObjectSize(new LongObjectContended()));
        System.out.println("VolatileLong: " + ObjectSizeCalculator.getObjectSize(new VolatileLong()));
        System.out.println(ObjectSizeCalculator.getObjectSize(new VolatileLongContented()));
        System.out.println(ObjectSizeCalculator.getObjectSize(new Sequence()));
        System.out.println("SequenceDemo:" + ObjectSizeCalculator.getObjectSize(new SequenceDemo()));
    }
}
