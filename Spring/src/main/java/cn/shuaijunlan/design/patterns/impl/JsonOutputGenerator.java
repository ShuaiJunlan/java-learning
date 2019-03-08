package cn.shuaijunlan.design.patterns.impl;

import cn.shuaijunlan.design.patterns.interfaces.IOutputGenerator;

/**
 * Created by Mr SJL on 2016/10/27.
 */
public class JsonOutputGenerator implements IOutputGenerator
{
    public void generateOutput()
    {
        System.out.println("Creating JsonOutputGenerator  Output......");
    }
}
