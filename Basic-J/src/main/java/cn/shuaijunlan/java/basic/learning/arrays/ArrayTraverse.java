package cn.shuaijunlan.java.basic.learning.arrays;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 1:43 PM 1/17/19.
 */
public class ArrayTraverse {
    private static long[][] arrs = new long[1024*1024][8];
    public static void main(String[] args) {
        long temp = 0;
        long start = System.currentTimeMillis();

        // Vertical traverse
        for (int i = 0; i < 8; i ++){
            for (int j = 0; j < 1024 * 1024; j++){
                temp = arrs[j][i];
            }
        }
        System.out.println("Vertical traverse spending time: " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        // Horizontal traverse
        for (int i = 0; i < 1024 * 1024; i++){
            for (int j = 0; j < 8; j++){
                temp = arrs[i][j];
            }
        }
        System.out.println("Horizontal traverse spending time: " + (System.currentTimeMillis() - start) + "ms");
    }
}
