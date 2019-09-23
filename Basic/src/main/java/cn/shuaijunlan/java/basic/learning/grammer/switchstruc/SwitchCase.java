package cn.shuaijunlan.java.basic.learning.grammer.switchstruc;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 19:21 2018/4/15.
 */
public class SwitchCase {
    public static void main(String[] args) {
        int a = 10;
        switch (a){
            case 10:
                System.out.println(10);
        }
    }
    public void test1(){
        long a = 100;
        // todo: required char byte short int Character Byte Short Integer String or Enum
        // switch (a){
        //     case 100:
        //         System.out.println(1);
        //         break;
        // }
    }

    public void test2(){
        String a = "100";
        // todo: required char byte short int Character Byte Short Integer String or Enum
        switch (a){
            case "100":
                System.out.println(1);
                break;
            default:
                break;
        }
    }
}
