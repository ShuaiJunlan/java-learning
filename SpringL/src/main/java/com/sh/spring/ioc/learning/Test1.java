package com.sh.spring.ioc.learning;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 16:37 2017/4/10.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class Test1
{
    @Test
    public void test1()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("ioc/InjectionBySetValue.xml");
        ApplicationContext context1 = new ClassPathXmlApplicationContext("ioc/InjectionBySetValue.xml");
        InjectBySetValue injectBySetValue = (InjectBySetValue) context.getBean("InjectionBySetValue");
        InjectBySetValue injectBySetValue1 = (InjectBySetValue) context1.getBean("InjectionBySetValue");
//        injectBySetValue.printName();
        System.out.println(injectBySetValue == injectBySetValue1);
    }

    @Test
    public void test2()
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ioc/InjectionBySetConstructor.xml");
        InjectionBySetConstructor injectionBySetConstructor = (InjectionBySetConstructor) context.getBean("InjectionBySetConstructor");
        context.destroy();
        InjectionBySetConstructor injectionBySetConstructor1 = (InjectionBySetConstructor) context.getBean("InjectionBySetConstructor");
        injectionBySetConstructor.printName();
        injectionBySetConstructor1.printName();
    }

    @Test
    public void test3()
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ioc/ApplicationContextAwareTest.xml");
        ApplicationContextAwareTest applicationContextAwareTest = (ApplicationContextAwareTest) context.getBean("ApplicationContextAwareTest");
        System.out.println(context.hashCode());

    }
    @Test
    public void test4()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("ioc/BeanAnnotation.xml");
        BeanAnnotation beanAnnotation = (BeanAnnotation) context.getBean("beanAnnotation");
        BeanAnnotation beanAnnotation1 = (BeanAnnotation) context.getBean("beanAnnotation");
        beanAnnotation.printNmae();
        System.out.println(beanAnnotation.hashCode());
        System.out.println(beanAnnotation1.hashCode());
    }
}
