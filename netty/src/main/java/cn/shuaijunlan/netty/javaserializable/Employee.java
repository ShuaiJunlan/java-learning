package cn.shuaijunlan.netty.javaserializable;

import java.io.Serializable;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 9:19 PM 11/4/18.
 */
public class Employee implements Serializable {
    private static final long serialVersionUID = 1111L;
    public String name;
    public String address;
    public transient int qq;
    public int number;

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", qq=" + qq +
                ", number=" + number +
                '}';
    }
}
