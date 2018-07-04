package cn.shuaijunlan.lanqiao.$2015.JAVA_A;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 22:$23 2017/3/1.
 */
public class $05 {
    public static void f(int n) {
        String s = "*";
        for (int i = 0; i < 2 * n - 3; i++) s += ".";
        s += "*";

//        String s1 = s + "\n";
//        String s2 = "";

//        for(int i=0; i<n-1; i++){
//            //System.out.println("=>"+s);
//            s = "." + _____________________________________ + "*";  //填空
//            s1 = s + "\n" + s1;
//            s2 += s + "\n";
//        }
//        System.out.println(s1+s2);
        System.out.println(s);
    }

    public static void main(String[] args) {
        f(8);
    }
}
