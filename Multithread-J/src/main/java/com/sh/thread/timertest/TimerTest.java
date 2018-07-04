package com.sh.thread.timertest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 9:53 2017/7/26.
 */
public class TimerTest {
    //  define a timer
    private static Timer timer = new Timer();

    // define MyTask class
    static public class MyTask extends TimerTask{
        private String str;
        public MyTask(String str){
            this.str = str;
        }

        @Override
        public void run() {
            System.out.println(this.str + "running:" + new Date());
        }
    }
    //  test main
    public static void main(String[] args) throws ParseException {
        MyTask myTask1 = new MyTask("task1");
        MyTask myTask2 = new MyTask("task2");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        String dateString1 = "2017-07-26 10:06:01";
        Date date1 = sdf.parse(dateString1);

        String dateString2 = "2017-07-26 10:05:01";
        Date date2 = sdf.parse(dateString2);

        timer.schedule(myTask1, date1);
        timer.schedule(myTask2, date2);
    }
}
