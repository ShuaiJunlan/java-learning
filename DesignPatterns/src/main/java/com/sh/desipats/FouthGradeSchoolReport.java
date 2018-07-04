package com.sh.desipats;

/**
 * Created by Mr SJL on 2016/11/15.
 */
public class FouthGradeSchoolReport extends SchoolReport
{

    public void report()
    {
        System.out.println("尊敬的xxx家长：");
        System.out.println("..........");
        System.out.println("语文62， 数学65， 体育98， 自然71");
        System.out.println("..........");
        System.out.println("家长签字：");
    }
    public void sign( String name)
    {
        System.out.println("家长姓名：" + name);
    }
}
