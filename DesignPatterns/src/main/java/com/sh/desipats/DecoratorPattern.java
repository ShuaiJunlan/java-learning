package com.sh.desipats;

/**
 * Created by Mr SJL on 2016/11/15.
 */
public class DecoratorPattern
{
    public static void main(String[] args)
    {
        SchoolReport sr;
        sr = new FouthGradeSchoolReport();
        sr = new HighScoreDecorator( sr );
        sr = new SortDecorator( sr );
        sr.report();
        sr.sign( "张三" );
    }


}
