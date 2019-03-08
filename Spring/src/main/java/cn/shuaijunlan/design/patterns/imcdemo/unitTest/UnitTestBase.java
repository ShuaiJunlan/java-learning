package cn.shuaijunlan.design.patterns.imcdemo.unitTest;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Mr SJL on 2016/11/26.
 *
 * @Author Junlan Shuai
 */
public class UnitTestBase
{
    private ClassPathXmlApplicationContext context;

    private String springXmlpath;
    public UnitTestBase()
    {

    }
    public UnitTestBase(String springXmlpath)
    {
        this.springXmlpath = springXmlpath;
    }
    @Before
    public void before()
    {
        if (StringUtils.isEmpty(springXmlpath))
        {
            springXmlpath = "classpath*:spring-*.xml";
        }
        try
        {
            context = new ClassPathXmlApplicationContext(springXmlpath.split("[,\\s]+"));
            context.start();
        }
        catch (BeansException e)
        {
            e.printStackTrace();
        }

    }
    @After
    public void after()
    {
        context.destroy();
    }

    protected <T extends Object> T getBean(String beanId)
    {
        return (T)context.getBean(beanId);
    }
    protected <T extends Object> T getBean(Class<T> clas)
    {
        return context.getBean(clas);
    }

}
