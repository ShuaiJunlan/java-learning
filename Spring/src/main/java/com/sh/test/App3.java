package com.sh.test;

import com.sh.services.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Mr SJL on 2016/10/27.
 */
public class App3
{
    public static void main( String[] args )
    {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"SpringCustomer.xml"});
        CustomerService cust = (CustomerService)context.getBean("tt");
        System.out.println(cust);

    }
}
