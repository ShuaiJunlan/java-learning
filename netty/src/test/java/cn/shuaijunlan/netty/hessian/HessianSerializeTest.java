package cn.shuaijunlan.netty.hessian;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 7:51 PM 10/29/18.
 */
public class HessianSerializeTest {
    private HessianSerialize hessianSerialize = new HessianSerialize();

    @Test
    public void serialize() throws Exception {


        byte[] val = hessianSerialize.serialize("Shuai Junlan");
        assertArrayEquals(val, hessianSerialize.serialize(hessianSerialize.deserialize(val)));
    }

    @Test
    public void deserialize() throws Exception {
        Cat cat = new Cat();
        cat.setAge(11);
        cat.setName("Shuai");
        cat.setSex("xy");

        Cat cat1 = new Cat();
        cat1.setAge(11);
        cat1.setName("Shuai");
        cat1.setSex("xy");

        assertEquals(cat, cat1);

        assertEquals(cat, hessianSerialize.deserialize(hessianSerialize.serialize(cat1)));
    }
}