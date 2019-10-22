package cn.shuaijunlan.grpc.leagning.helloworld;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 3:26 PM 10/20/19.
 */
public class ProtobufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        HelloRequest request = HelloRequest.newBuilder().setName("Shuai Junlan").build();
        System.out.println(request.toByteArray().length);
        System.out.println(HelloRequest.parseFrom(request.toByteArray()).getName());
    }
}
