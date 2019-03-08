package cn.shuaijunlan.design.patterns.spring.ioc.learning;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 15:39 2017/4/12.
 */
public class ApplicationContextAwareTest implements ApplicationContextAware
{
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        ClassPathXmlApplicationContext context = (ClassPathXmlApplicationContext) applicationContext;
        System.out.println(context.hashCode());
//        context.destroy();
    }
}
