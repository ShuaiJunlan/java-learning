package cn.shuaijunlan.design.patterns.imcdemo.dao.impl;

import cn.shuaijunlan.design.patterns.imcdemo.dao.interfaces.InjectionDao;

/**
 * Created by Mr SJL on 2016/11/26.
 *
 * @Author Junlan Shuai
 */
public class InjectionDaoImpl implements InjectionDao
{
    public void insert(String name)
    {
        System.out.println("保存名字:‘" + name + "’到mysql数据库");
    }
}
