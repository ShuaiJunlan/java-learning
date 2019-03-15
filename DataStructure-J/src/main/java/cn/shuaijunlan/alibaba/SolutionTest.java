package cn.shuaijunlan.alibaba;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:19 PM 3/14/19.
 */
public class SolutionTest {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Solution solution = new Solution();
        solution.startUp();
        System.out.println("Spending time: " + (System.currentTimeMillis() - start) + "ms");
    }
}
