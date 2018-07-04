package com.sh.imcdemo.services.impl;

import com.sh.imcdemo.dao.interfaces.InjectionDao;
import com.sh.imcdemo.services.interfaces.InjectionService;

/**
 * Created by Mr SJL on 2016/11/26.
 *
 * @Author Junlan Shuai
 */
public class InjectionServiceImpl implements InjectionService
{
    private InjectionDao injectionDao;
    public  InjectionServiceImpl()
    {

    }
    public InjectionServiceImpl(InjectionDao injectionDao)
    {
        this.injectionDao = injectionDao;
    }

    public void setInjectionDao(InjectionDao injectionDao)
    {
        this.injectionDao = injectionDao;
    }

    public void saveName(String name)
    {
        this.injectionDao.insert(name);

    }
}
