package cn.shuaijunlan.kryo.learning;

import cn.shuaijunlan.netty.javaserializable.Employee;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 7:48 PM 11/11/18.
 */
public class KryoTest {
    public static void main(String[] args) {
        Kryo kryo = new Kryo();
        kryo.register(Employee.class);

        Employee employee = new Employee();
        employee.name = "shuai";
        employee.address = "China";
        employee.qq = 222;
        employee.number = 121;
        System.out.println(employee.toString());

        try {
            Output output = new Output(new FileOutputStream("file.bin"));
            kryo.writeObject(output, employee);
            output.close();

            Input input = new Input(new FileInputStream("file.bin"));
            Employee employee1 = kryo.readObject(input, Employee.class);
            input.close();

            System.out.println(employee1.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
