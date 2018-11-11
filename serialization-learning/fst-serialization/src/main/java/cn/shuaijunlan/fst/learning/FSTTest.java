package cn.shuaijunlan.fst.learning;

import cn.shuaijunlan.netty.javaserializable.Employee;
import org.nustaq.serialization.FSTConfiguration;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 7:30 PM 11/11/18.
 */
public class FSTTest {
    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.name = "shuai";
        employee.address = "China";
        employee.qq = 222;
        employee.number = 121;
        System.out.println(employee.toString());
        FSTConfiguration configuration = FSTConfiguration.createDefaultConfiguration();

        byte[] array = configuration.asByteArray(employee);

        Employee employee1 = (Employee) configuration.asObject(array);
        System.out.println(employee1.toString());
    }
}
