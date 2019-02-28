package cn.shuaijunlan.netty.javaserializable;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 9:23 PM 11/4/18.
 */
public class EmployeeTest {

    @Test
    public void test1(){
        Employee employee = new Employee();
        employee.name = "shuai";
        employee.address = "China";
        employee.qq = 222;
        employee.number = 121;

        EmployeeMan employeeMan = new EmployeeMan();

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./employee.ser");
            // ByteArrayOutputStream fileOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(employee);
            // System.out.println(fileOutputStream.toByteArray().length);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Serialized data is saved in ./employee.ser");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        try {
            FileInputStream fileInputStream = new FileInputStream("./employee.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Employee e = (Employee) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            System.out.println(e.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test3(){
        EmployeeMan employeeMan = new EmployeeMan();
        employeeMan.name = "shuai";

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./employeeMan.ser");
            // ByteArrayOutputStream fileOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(employeeMan);
            // System.out.println(fileOutputStream.toByteArray().length);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Serialized data is saved in ./employeeMan.ser");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}