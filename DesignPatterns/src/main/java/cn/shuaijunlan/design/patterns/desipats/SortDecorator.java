package cn.shuaijunlan.design.patterns.desipats;

/**
 * Created by Mr SJL on 2016/11/15.
 */
public class SortDecorator extends Decorator
{
    public SortDecorator( SchoolReport sr)
    {
        super(sr);
    }
    private void reportSort()
    {
        System.out.println("这次考试班级排名为35名");
    }
    @Override
    public void report()
    {
        super.report();
        this.reportSort();
    }
}
