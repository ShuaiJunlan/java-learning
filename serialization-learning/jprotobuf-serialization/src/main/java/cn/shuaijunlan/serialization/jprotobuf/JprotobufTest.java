package cn.shuaijunlan.serialization.jprotobuf;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;

import java.io.IOException;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 1:03 PM 10/20/19.
 */
public class JprotobufTest {
    public static void main(String[] args) {
        Codec<SimpleTypeTest> simpleTypeTestCodec = ProtobufProxy.create(SimpleTypeTest.class);
        SimpleTypeTest simpleTypeTest = new SimpleTypeTest();
        simpleTypeTest.setName("shaui");
        simpleTypeTest.setValue(109);

        byte[] bytes;
        try {
            bytes = simpleTypeTestCodec.encode(simpleTypeTest);
            SimpleTypeTest newSim = simpleTypeTestCodec.decode(bytes);
            System.out.println(simpleTypeTest.toString().equals(newSim.toString()));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
