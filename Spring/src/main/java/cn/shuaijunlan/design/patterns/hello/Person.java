package cn.shuaijunlan.design.patterns.hello;

/**
 * Created by Mr SJL on 2016/10/27.
 */
public class Person
{
    private String name;
    private String address;
    private int age;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }
    public String toString ()
    {
        return "Person[name:"+name+"  ;address:"+address+"  ;age:"+age+"]";
    }
}
