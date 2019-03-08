package cn.shuaijunlan.design.patterns.imcdemo.unitTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * Created by Mr SJL on 2016/11/26.
 *
 * @Author Junlan Shuai
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class App3 extends UnitTestBase
{
    public App3()
    {
        super("classpath:spring-lifecycle.xml");
    }
    @Test
    public void test1()
    {
        super.getBean("beanLifeCycle");
    }

    @Test
    public void test2()
    {
        super.getBean("beanLifeCycle1");
    }

}
