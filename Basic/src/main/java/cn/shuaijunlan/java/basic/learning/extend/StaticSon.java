package cn.shuaijunlan.java.basic.learning.extend;

/**
 * static和final修饰的方法不能被重写
 */
public class StaticSon extends StaticParent {
    static void getName(){
        System.out.println("A");
    }

    @Override
    void getSex(){
        System.out.println("man");
    }

//    final void getAge(){
//        System.out.println("19");
//    }
}
