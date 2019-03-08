package cn.shuaijunlan.design.patterns.thread.impl;

/**
 * Created by Mr SJL on 2016/11/19.
 *
 * @Author Junlan Shuai
 */
public class LoginServlet {
    private static String nameR;
    private static String passwordR;

    public static void doPost(String name, String password) {
        try {
            nameR = name;
            if (name.equals("a")) {
                Thread.sleep(5000);
            }
            passwordR = password;
            System.out.println("name= " + name + ";password= " + password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
