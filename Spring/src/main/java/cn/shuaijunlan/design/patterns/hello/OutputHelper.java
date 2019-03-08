package cn.shuaijunlan.design.patterns.hello;

import cn.shuaijunlan.design.patterns.interfaces.IOutputGenerator;

/**
 * Created by Mr SJL on 2016/10/27.
 */
public class OutputHelper
{
    IOutputGenerator outputGenerator;
    public void generateOutput()
    {
        this.outputGenerator.generateOutput();
    }
    public void setOutputGenerator (IOutputGenerator outputGenerator)
    {
        this.outputGenerator = outputGenerator;
    }
}
