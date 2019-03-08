package cn.shuaijunlan.design.patterns.test;

import cn.shuaijunlan.design.patterns.hello.OutputHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Mr SJL on 2016/10/27.
 */
public class App1
{
    private static ApplicationContext context;
    public static void  main(String[] args)
    {
        context = new ClassPathXmlApplicationContext(new String[]{"Spring-Output.xml"});
        OutputHelper outputHelper = (OutputHelper)context.getBean("OutputHelper");
        outputHelper.generateOutput();

    }
}
