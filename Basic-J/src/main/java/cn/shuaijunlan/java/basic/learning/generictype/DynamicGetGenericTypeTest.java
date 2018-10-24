package cn.shuaijunlan.java.basic.learning.generictype;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 11:08 AM 10/24/18.
 */
public class DynamicGetGenericTypeTest {
    @Test
    public void test1(){
        GetResultImpl result = new GetResultImpl();

        //get implement interface
        Type[] types = result.getClass().getGenericInterfaces();
        System.out.println(Arrays.toString(types));

        //get extend superclass
        Type types1 = result.getClass().getGenericSuperclass();
        System.out.println(types1.toString());

        //get actual type
        Type[] params = ((ParameterizedType) types[0]).getActualTypeArguments();
        System.out.println(Arrays.toString(params));
    }
}
