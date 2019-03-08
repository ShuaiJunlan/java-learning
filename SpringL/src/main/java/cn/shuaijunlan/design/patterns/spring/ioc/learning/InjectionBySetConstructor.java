package cn.shuaijunlan.design.patterns.spring.ioc.learning;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 16:49 2017/4/10.
 */
public class InjectionBySetConstructor
{
    public String name;
    public InjectionBySetConstructor(String name)
    {
       this.name = name;
    }
    public void printName()
    {
        System.out.println(name);
    }

}
