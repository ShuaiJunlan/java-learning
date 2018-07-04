package cn.shuaijunlan.lanqiao.$2017;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 10:09 2017/3/18.
 */
public class $03 {
    public static long[] factorial = new long[18];

    public static void getFactorial() {
        factorial[0] = 1;
        for (int i = 1; i < factorial.length; i++) {
            factorial[i] = i * factorial[i - 1];
        }
    }

    public static long getValue(String str) {
        char[] chars = str.toCharArray();
        int[] mark = new int[str.length()];
        long sum = 0, count = 0;
        for (int j = 0, k = chars.length - 1; j < chars.length; j++, k--) {
            for (int i = 'a'; i < chars[j]; i++) {
                if (mark[i - 'a'] != 1)
                    count++;
            }
            mark[chars[j] - 'a'] = 1;
            sum = sum + count * factorial[k];
            count = 0;
        }
        return sum;
    }

    public static void main(String[] args) {
        getFactorial();
        System.out.println(getValue("bckfqlajhemgiodnp"));
        System.out.println(factorial[17]);
    }
    /**
     * Answer:22952601027516
     */
}