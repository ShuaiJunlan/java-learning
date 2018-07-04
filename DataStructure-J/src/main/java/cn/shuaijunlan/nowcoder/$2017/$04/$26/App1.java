package cn.shuaijunlan.nowcoder.$2017.$04.$26;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 20:00 2017/4/26.
 */

import java.util.LinkedList;
import java.util.Scanner;

public class App1 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        if (line != null && !line.isEmpty()) {
            int res = resolve(line.trim());
            System.out.println(String.valueOf(res));
        }
    }

    // write your code here
    public static int resolve(String expr) {
        int size = 0;
        LinkedList<Integer> linkedList = new LinkedList<>();
        String[] strs = expr.split(" ");
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].equals("+")) {
                if (linkedList.size() < 2)
                    return -1;
                int a = linkedList.removeLast();
                int b = linkedList.removeLast();
                linkedList.addLast(a + b);
                size--;
            } else if (strs[i].equals("^")) {
                if (linkedList.size() < 1)
                    return -1;
                int a = linkedList.removeLast();
                linkedList.addLast(a + 1);
            } else if (strs[i].equals("*")) {
                if (linkedList.size() < 2)
                    return -1;
                int a = linkedList.removeLast();
                int b = linkedList.removeLast();
                linkedList.addLast(a * b);
                size--;
            } else if (strs[i].equals("")) {
                continue;
            } else {
                linkedList.addLast(Integer.valueOf(strs[i]));
                size++;
                if (size > 16)
                    return -2;
            }
        }
        return linkedList.getLast();
    }
}
