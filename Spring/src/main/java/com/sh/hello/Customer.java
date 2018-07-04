package com.sh.hello;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Mr SJL on 2016/10/27.
 */
public class Customer
{
    private List<Object> lists ;//这里的lists要和Bean中property标签的name一样。详见本段代码下的注释。
    private Set<Object> sets ;
    private Map<Object, Object> maps ;
    private Properties pros;

    public List<Object> getLists()
    {
        return lists;
    }
    public void setLists(List<Object> lists)
    {
        this.lists = lists;
    }
    public Set<Object> getSets()
    {
        return sets;
    }
    public void setSets(Set<Object> sets)
    {
        this.sets = sets;
    }
    public Map<Object, Object> getMaps()
    {
        return maps;
    }
    public void setMaps(Map<Object, Object> maps)
    {
        this.maps = maps;
    }
    public Properties getPros()
    {
        return pros;
    }
    public void setPros(Properties pros)
    {
        this.pros = pros;
    }
    private Person person;
    public Customer()
    {

    }
    public Customer(Person person)
    {
        this.person = person;
    }
    public void setPerson(Person person)
    {
        this.person = person;
    }

    public String toString()
    {
        return "Customer [Person=" + person +"]";
    }

}
