package cn.shuaijunlan.netty.protostuff;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 10:00 AM 11/3/18.
 */
public class FooTest {
    @Test
    public void roundTrip() {
        Foo foo = new Foo("foo", 1);

        // this is lazily created and cached by RuntimeSchema
        // so its safe to call RuntimeSchema.getSchema(Foo.class) over and over
        // The getSchema method is also thread-safe
        Schema<Foo> schema = RuntimeSchema.getSchema(Foo.class);

        // Re-use (manage) this buffer to avoid allocating on every serialization
        LinkedBuffer buffer = LinkedBuffer.allocate(512);

        // ser
        final byte[] protostuff;
        try
        {
            protostuff = ProtostuffIOUtil.toByteArray(foo, schema, buffer);
        }
        finally
        {
            buffer.clear();
        }

        // deser
        Foo fooParsed = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(protostuff, fooParsed, schema);
        System.out.println(fooParsed.toString());
        assertEquals(foo, fooParsed);
    }

}