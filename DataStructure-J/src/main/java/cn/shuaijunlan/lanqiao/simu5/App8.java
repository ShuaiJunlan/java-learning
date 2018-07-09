package cn.shuaijunlan.lanqiao.simu5;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 16:54 2017/4/4.
 */
public class App8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> hashMap = new HashMap<>();
        String[] strs;
        while (scanner.hasNext()) {
            String a = scanner.nextLine();
            switch (a.charAt(0)) {
                case 'i':
                    strs = a.split(" ");
                    if (hashMap.containsKey(strs[1])) {
                        if (hashMap.get(strs[1]) < Integer.valueOf(strs[2])) {
                            hashMap.put(strs[1], Integer.valueOf(strs[2]));
                        }
                    } else
                        hashMap.put(strs[1], Integer.valueOf(strs[2]));
                    break;
                case 'f':
                    strs = a.split(" ");
                    if (!hashMap.containsKey(strs[1]))
                        System.out.println(-1);
                    else
                        System.out.println(hashMap.get(strs[1]));
                    break;
                case 'e':
                    return;
            }
        }
    }
}
