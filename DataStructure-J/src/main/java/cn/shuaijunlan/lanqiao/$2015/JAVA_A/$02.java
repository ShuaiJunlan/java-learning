package cn.shuaijunlan.lanqiao.$2015.JAVA_A;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 20:27 2017/3/1.
 */
public class $02 {
    public boolean isYears(int num) {
        if (num % 400 == 0)
            return true;
        if (num % 4 == 0 && num % 100 != 0)
            return true;
        return false;
    }

    public void getYear(int num) {

    }
}
