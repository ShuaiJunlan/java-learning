package com.sh.$17.$08.$31;

import java.util.HashSet;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 19:00 2017/8/31.
 */
public class App1 {
    public static void main(String[] args) {
        int num = 0;
        for(int i = 3000; i < 4000; i++){
            char[] a = String.valueOf(i).toCharArray();
            HashSet hashSet = new HashSet<Character>();
            for (int j = 0; j < a.length; j++){
                hashSet.add(a[j]);
            }
            if (hashSet.size() == 3) num++;
        }
        System.out.println(num);
    }
}
