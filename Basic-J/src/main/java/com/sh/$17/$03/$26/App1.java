package com.sh.$17.$03.$26;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 15:34 2017/3/26.
 */
public class App1 extends Thread
{
    int value;
    App2 app2 = new App2();
    public App1(int value)
    {
        this.value = value;
    }
    @Override
    public void run()
    {
        if (value == 1)
            System.out.println(app2.getValue());
        else
            app2.setValue(value);
    }
    public static void main(String[] args)
    {
//        String str = "hello world";
//        System.out.println(str.indexOf('h'));
//        System.out.println('a' - 'A');
        App1 app1 = new App1(1);
        for (int  i = 2; i < 100; i++)
        {
            App1 app2 = new App1(i);
            app2.start();
            app1.start();
        }
    }
}