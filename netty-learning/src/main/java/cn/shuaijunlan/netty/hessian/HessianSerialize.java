package cn.shuaijunlan.netty.hessian;

import com.alibaba.com.caucho.hessian.io.HessianInput;
import com.alibaba.com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 7:50 PM 10/29/18.
 */
public class HessianSerialize {
    public  byte[] serialize(Object obj)throws Exception{
        if( obj == null){
            throw new NullPointerException();
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(os);
        ho.writeObject(obj);
        return os.toByteArray();
    }
    public  Object deserialize(byte[] by) throws IOException {
        if(by==null) throw new NullPointerException();

        ByteArrayInputStream is = new ByteArrayInputStream(by);
        HessianInput hi = new HessianInput(is);
        return hi.readObject();
    }
}
