package cn.shuaijunlan.design.patterns.imcdemo.services.impl;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by Mr SJL on 2016/11/26.
 *
 * @Author Junlan Shuai
 */
public class BeanLifeCycle implements InitializingBean, DisposableBean
{
    public void destroy() throws Exception
    {
        System.out.println("Bean destory.");
    }

    public void afterPropertiesSet() throws Exception
    {
        System.out.println("Bean afterPropertiesSet.");

    }
    public void start()
    {
        System.out.println("Bean start.");
    }
    public void stop()
    {
        System.out.println("Bean stop.");
    }

    public void defaultInit()
    {
        System.out.println("Bean defaultInit.");
    }
    public void defaultDestroy()
    {
        System.out.println("Bean defaultDestory");
    }

}
