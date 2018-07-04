package cn.shuaijunlan.lanqiao.exercise.algorithm;


/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 20:28 2017/3/15.
 */

/**
 * 背包问题：01背包问题、完全背包问题
 */
public class Backpack {
    public static int W = 0, N = 0;
    public static int[][] select;
    public static Good[] goods;

    static class Good {
        int value;
        int weight;

        public Good() {

        }

        public Good(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    //获取两个数的大者
    int getMax(int a, int b) {
        return a >= b ? a : b;
    }

    /**
     * 01背包问题
     * 对于每个物品只有放和不放两种情况
     */
    public static int get01BackPack(int goods_num, int bag_weight) {
        int[] sum = new int[bag_weight + 1];
        for (int i = 1; i <= goods_num; i++) {
            for (int j = bag_weight; j > 0; j--) {
                if (goods[i].weight <= j)
                    sum[j] = getMaxValueInTwo(sum[j], sum[j - goods[i].weight] + goods[i].value);
            }
        }
        return sum[bag_weight];
    }

    public static int get01BackPack1(int goods_num, int bag_weight) {
        int[][] sum = new int[goods_num + 1][bag_weight + 1];
        for (int i = 1; i <= goods_num; i++) {
            for (int j = bag_weight; j > 0; j--) {
                if (goods[i].weight <= j)
                    sum[i][j] = getMaxValueInTwo(sum[i - 1][j], sum[i - 1][j - goods[i].weight] + goods[i].value);
                else                //  这个条件不能忘记
                    sum[i][j] = sum[i - 1][j];
            }
        }
        return sum[goods_num][bag_weight];
    }

    /**
     * 完全背包问题
     * 每种物品可以放多次
     */
    public static int getAllBackPack(int goods_num, int bag_weight) {
        int[] f = new int[bag_weight + 1];

        for (int i = 1; i <= goods_num; i++) {
            for (int j = goods[i].weight; j <= bag_weight; j++) {
                f[j] = Math.max(f[j], f[j - goods[i].weight] + goods[i].value);
            }
        }
        return f[bag_weight];
    }


    public static int getMaxValueInTwo(int a, int b) {
        return a > b ? a : b;
    }

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
        int goods_num = 5;
        int bag_weight = 10;

        goods = new Good[goods_num + 1];
        for (int i = 0; i < goods_num; i++)
            goods[i + 1] = new Good();
        goods[1].weight = 3;
        goods[1].value = 6;

        goods[2].weight = 4;
        goods[2].value = 8;

        goods[3].weight = 6;
        goods[3].value = 7;

        goods[4].weight = 2;
        goods[4].value = 5;

        goods[5].weight = 5;
        goods[5].value = 9;
//        for (int i = 0; i < goods_num; i++)
//        {
//            goods[i + 1] = new Good();
//
//            goods[i + 1].weight = scanner.nextInt();
//            goods[i + 1].value = scanner.nextInt();
//        }
        System.out.println(get01BackPack(goods_num, bag_weight));
        System.out.println(get01BackPack1(goods_num, bag_weight));
        System.out.println(getAllBackPack(goods_num, bag_weight));
    }

}
