package cn.shuaijunlan.java.basic.learning.extend;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 9:07 AM 10/29/18.
 */
public class SonA extends Parent {
    private String name;

    public SonA(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println("Son:" + name);
    }

    public static void main(String[] args) {
        Parent parent = new Parent("p");
        parent.printName();
        parent.printStatic();
        Parent.printStatic();

        SonA sonA = new SonA("s");
        sonA.printName();

        Parent son = new SonA("ss");
        son.printName();
    }
}
