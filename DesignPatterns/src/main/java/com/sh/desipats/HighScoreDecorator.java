package com.sh.desipats;

/**
 * Created by Mr SJL on 2016/11/15.
 */
public class HighScoreDecorator extends Decorator
{
    public HighScoreDecorator(SchoolReport sr)
    {
        super(sr);
    }
    private void reportHighScore()
    {
        System.out.println("这次考试语文最高分为75");
    }
    @Override
    public void report()
    {
        this.reportHighScore();
        super.report();
    }
}
