package cn.shuaijunlan.lanqiao.exercise.senior;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 9:14 2017/3/16.
 */

/**
 * 部分背包问题：使用贪心算法求解
 */
public class ADV_167 {
    public static Good[] goods;

    static class Good implements Comparable {
        float aver_value;
        float weight;

        @Override
        public int compareTo(Object o) {
            Good temp = (Good) o;
            if (this.aver_value > temp.aver_value)
                return -1;
            else if (this.aver_value < temp.aver_value)
                return 1;
            return 0;
        }
    }

    public static float getMaxValue(int googs_num, float bag_weight) {
        float weight = 0.0f;
        float value = 0.0f;
        for (int i = 0; i < googs_num; i++) {
            if ((weight + goods[i].weight) <= bag_weight) {
                value += (goods[i].weight * goods[i].aver_value);
                weight += goods[i].weight;
            } else {
                value += (bag_weight - weight) * goods[i].aver_value;
                break;
            }
        }
        return value;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int goods_num = scanner.nextInt();
        float bag_weight = scanner.nextInt();
        goods = new Good[goods_num];
        for (int i = 0; i < goods_num; i++) {
            goods[i] = new Good();
        }

        for (int i = 0; i < goods_num; i++) {
            float weight = scanner.nextInt();
            float value = scanner.nextInt();

            goods[i].weight = weight;
            goods[i].aver_value = value / weight;
        }
        Arrays.sort(goods);
        System.out.println(String.format("%.1f", getMaxValue(goods_num, bag_weight)));
    }
}
