package cn.shuaijunlan.lanqiao.simu5;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 15:30 2017/4/1.
 */
public class App2 {
    public static void main(String[] args) {
        int re = Integer.MAX_VALUE;
        for (int x = 1; x < 300; x++) {
            for (int y = 1; y < 300; y++) {
                for (int z = 1; z < 300; z++) {
                    if (11 * x + 13 * y + 17 * z == 2471 && 13 * x + 17 * y + 11 * z == 2739) {
                        re = re < (x + y + z) ? re : (x + y + z);
                        System.out.println(x + ":" + y + ":" + z);
                    }
                }
            }
        }
        System.out.println(re);
        // answer:181
    }
}
