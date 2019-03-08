package cn.shuaijunlan.design.patterns.test;

import cn.shuaijunlan.design.patterns.hello.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Mr SJL on 2016/10/27.
 */
public class App2
{
    private static ApplicationContext context;
    public static void main(String[] args)
    {
        context = new ClassPathXmlApplicationContext(new String[]{"SpringCollections.xml"});
        Customer customer = (Customer) context.getBean("customerBean");
        System.out.println(customer.getLists().toString());
        System.out.println(customer.getMaps().toString());
        System.out.println(customer.getPros().toString());
        System.out.println(customer.getSets().toString());


    }
}
