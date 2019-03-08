package cn.shuaijunlan.design.patterns.spring.ioc.learning;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 15:50 2017/4/15.
 */
@Component
@Scope("singleton")
public class BeanAnnotation
{
    public void printNmae()
    {
        System.out.println("shuaijunlan");
    }
}
