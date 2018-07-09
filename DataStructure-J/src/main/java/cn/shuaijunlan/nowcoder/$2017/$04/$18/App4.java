package cn.shuaijunlan.nowcoder.$2017.$04.$18;

import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 19:47 2017/4/18.
 */
public class App4 {
    static private int[][] nums = {
            // 1        2       3       4       5       6       7       8       9       0       +       -       *       /       =       .
            {0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0},
            {0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1},
            {0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}
    };

    public static void getRe(int a, int b, String mark) {
        int reI;
        float reF;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a + "" + mark + b + "=");

        if (mark.equals("+")) {
            reI = a + b;
            stringBuilder.append(reI);
        } else if (mark.equals("-")) {
            reI = a - b;
            stringBuilder.append(reI);
        } else if (mark.equals("*")) {
            reI = a * b;
            stringBuilder.append(reI);
        } else if (mark.equals("/")) {
            if (a % b == 0) {
                reI = a / b;
                stringBuilder.append(reI);
            } else {
                reF = (float) a / b;
                stringBuilder.append(reF);
            }
        }
        print(stringBuilder.toString().toCharArray());
    }

    public static void getLine(int line, int a, int b, StringBuilder stringBuilder) {
        for (; a < b; a++)
            if (nums[line][a] == 1)
                stringBuilder.append("*");
            else
                stringBuilder.append(" ");
        stringBuilder.append(" ");
    }

    public static void print(char[] str) {
        for (int i = 0; i < 5; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < str.length; j++) {
                switch (str[j]) {

                    case '1': {
                        getLine(i, 0, 3, stringBuilder);
                        break;
                    }
                    case '2': {
                        getLine(i, 3, 6, stringBuilder);
                        break;
                    }
                    case '3': {
                        getLine(i, 6, 9, stringBuilder);
                        break;
                    }
                    case '4': {
                        getLine(i, 9, 12, stringBuilder);
                        break;
                    }
                    case '5': {
                        getLine(i, 12, 15, stringBuilder);
                        break;
                    }
                    case '6': {
                        getLine(i, 15, 18, stringBuilder);
                        break;
                    }
                    case '7': {
                        getLine(i, 18, 21, stringBuilder);
                        break;
                    }
                    case '8': {
                        getLine(i, 21, 24, stringBuilder);
                        break;
                    }
                    case '9': {
                        getLine(i, 24, 27, stringBuilder);
                        break;
                    }
                    case '0': {
                        getLine(i, 27, 30, stringBuilder);
                        break;
                    }
                    case '+': {
                        getLine(i, 30, 33, stringBuilder);
                        break;
                    }
                    case '-': {
                        getLine(i, 33, 36, stringBuilder);
                        break;
                    }
                    case '*': {
                        getLine(i, 36, 39, stringBuilder);
                        break;
                    }
                    case '/': {
                        getLine(i, 39, 42, stringBuilder);
                        break;
                    }
                    case '=': {
                        getLine(i, 42, 45, stringBuilder);
                        break;
                    }
                    case '.': {
                        getLine(i, 45, 48, stringBuilder);
                        break;
                    }
                }
            }
            System.out.println(stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();

        int a, b;
        if (temp.contains("+")) {
            int t = temp.indexOf("+");
            a = Integer.valueOf(temp.substring(0, t - 1));
            b = Integer.valueOf(temp.substring(t + 2, temp.length()));
            getRe(a, b, "+");
        } else if (temp.contains("-")) {
            int t = temp.indexOf("-");
            a = Integer.valueOf(temp.substring(0, t - 1));
            b = Integer.valueOf(temp.substring(t + 2, temp.length()));
            getRe(a, b, "-");
        } else if (temp.contains("*")) {
            int t = temp.indexOf("*");
            a = Integer.valueOf(temp.substring(0, t - 1));
            b = Integer.valueOf(temp.substring(t + 2, temp.length()));
            getRe(a, b, "*");
        } else if (temp.contains("/")) {
            int t = temp.indexOf("/");
            a = Integer.valueOf(temp.substring(0, t - 1));
            b = Integer.valueOf(temp.substring(t + 2, temp.length()));
            getRe(a, b, "/");
        }
    }
}
