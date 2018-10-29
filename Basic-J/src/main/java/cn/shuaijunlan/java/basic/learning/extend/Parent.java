package cn.shuaijunlan.java.basic.learning.extend;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 9:06 AM 10/29/18.
 */
public class Parent {
    private String name;
    public Parent(String name){
        this.name = name;
    }
    public void printName(){
        System.out.println("Parent:" + name);
    }

    public static void printStatic(){
        System.out.println("Static");
    }
}
