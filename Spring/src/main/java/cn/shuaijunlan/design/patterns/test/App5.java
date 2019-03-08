package cn.shuaijunlan.design.patterns.test;

import cn.shuaijunlan.design.patterns.services.CustomerSvr;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Mr SJL on 2016/11/1.
 */
public class App5
{
    public static void main(String[] args)
    {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"SpringAOPAdviceAuto.xml"});
        CustomerSvr customerSvr = (CustomerSvr) context.getBean("customerSvr");
        System.out.println("**********************");
        customerSvr.printName();
        System.out.println("**********************");
        customerSvr.printURL();
        System.out.println("**********************");
        try
        {
            customerSvr.printThrowException();
        }
        catch (Exception e)
        {

        }
    }
}
