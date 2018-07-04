package com.sh.imcdemo.unitTest;

import com.sh.imcdemo.services.impl.BeanScope;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * Created by Mr SJL on 2016/11/26.
 *
 * @Author Junlan Shuai
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class App2 extends UnitTestBase
{
    public App2()
    {
        super("classpath:spring-scope.xml");
    }
    @Test
    public void test1()
    {
        BeanScope beanScope = super.getBean("beanScope");
        beanScope.printClassHashCode();
        beanScope = super.getBean("beanScope");
        beanScope.printClassHashCode();
    }
    @Test
    public  void test2()
    {
        BeanScope beanScope = super.getBean("beanScope1");
        beanScope.printClassHashCode();
        beanScope = super.getBean("beanScope1");
        beanScope.printClassHashCode();
    }

}
