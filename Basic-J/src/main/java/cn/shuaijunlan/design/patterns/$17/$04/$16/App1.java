package cn.shuaijunlan.design.patterns.$17.$04.$16;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 11:49 2017/4/16.
 */
public class App1
{
    public static LinkedList<Integer> arrayList = new LinkedList<>();
    public static void main(String[] args)
    {
//        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < 10; i++)
        {
            arrayList.add(i);
        }
//        for (int j : arrayList)
//        {
//
//        }
//        for (int i = 0; i < arrayList.size(); i++)
//        {
//            if (i == 7)
//                arrayList.remove(i);
//        }
        Iterator<Integer> iterator = arrayList.iterator();
        for (;iterator.hasNext();)
        {
            int i = iterator.next();
            if (i == 7)
                arrayList.remove(i);
        }
    }
}
