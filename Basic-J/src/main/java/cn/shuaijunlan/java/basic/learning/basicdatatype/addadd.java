package cn.shuaijunlan.java.basic.learning.basicdatatype;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 12:48 PM 11/2/18.
 */
public class addadd {
    static int x, y;
    static {
        int x = 5;
        x--;
    }
    static {
        x--;
    }

    public static void main(String[] args) {
        x--;
        add();
        System.out.println(x+y + ++x);
    }
    static void add(){
        y = x++ + ++x; //y == -2 ??
    }
}
