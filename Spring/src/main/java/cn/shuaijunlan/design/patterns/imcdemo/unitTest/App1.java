package cn.shuaijunlan.design.patterns.imcdemo.unitTest;

import cn.shuaijunlan.design.patterns.imcdemo.services.interfaces.InjectionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * Created by Mr SJL on 2016/11/26.
 *
 * @Author Junlan Shuai
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class App1 extends UnitTestBase
{
    public App1()
    {
        super("classpath:spring-injection.xml");
    }

    /**
     * 用于测试同set的方式注入对象
     */
    @Test
    public void test1()
    {
        InjectionService injectionService = super.getBean("injectionService");
        injectionService.saveName("Junlan Shuai");
    }

    /**
     * 用于测试通过构造函数的方式注入对象
     */
    @Test
    public void test2()
    {
        InjectionService injectionService = super.getBean("injectionServiceC");
        injectionService.saveName("shuaijunlan");
    }
}
