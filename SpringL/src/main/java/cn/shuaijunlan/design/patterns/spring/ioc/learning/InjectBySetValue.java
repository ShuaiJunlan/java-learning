package cn.shuaijunlan.design.patterns.spring.ioc.learning;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 16:33 2017/4/10.
 */
public class InjectBySetValue
{
    public String name;
    public void setName(String name)
    {
        this.name = name;
    }
    public void printName()
    {
        System.out.println(name);
    }
}
