package com.sh.test;

import com.sh.services.CustomerSvr;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Mr SJL on 2016/10/28.
 */
public class App4
{
    public static void main(String[] args)
    {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"SpringAOPAdvice.xml"});
        CustomerSvr customerSvr = (CustomerSvr) context.getBean("customerServiceProxy");
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
