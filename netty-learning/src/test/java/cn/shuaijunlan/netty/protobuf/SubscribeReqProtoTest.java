package cn.shuaijunlan.netty.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 2:40 PM 10/21/18.
 */
// @Slf4j
public class SubscribeReqProtoTest {
    /**
     * encode
     * @param req
     * @return
     */
    private static byte[] encode(SubscribeReqProto.SubscribeReq req){
        return req.toByteArray();
    }

    /**
     * decode
     * @param body
     * @return
     * @throws InvalidProtocolBufferException
     */
    private static SubscribeReqProto.SubscribeReq decode(byte[] body) throws InvalidProtocolBufferException {
        return SubscribeReqProto.SubscribeReq.parseFrom(body);
    }

    private static SubscribeReqProto.SubscribeReq createSubscribeReq(){
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqID(1);
        builder.setUserName("shuaijunlan");
        builder.setProductName("Hello");
        List<String> address = new ArrayList<>();
        address.add("Jiangxi");
        address.add("JiuJiang");
        address.add("ChaiSang");

        builder.addAllAddress(address);
        return builder.build();

    }

    @Test
    public void test() throws InvalidProtocolBufferException {
        SubscribeReqProto.SubscribeReq req = createSubscribeReq();
        System.out.println("Before encode: " + req.toString());
        SubscribeReqProto.SubscribeReq req1 = decode(encode(req));
        System.out.println("After encode and decode: " + req.toString());

        assertEquals(req1, req);
    }



}