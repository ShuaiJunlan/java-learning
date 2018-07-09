package cn.shuaijunlan.nowcoder.$2017.$04.$21;

import java.util.ArrayList;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 20:24 2017/4/21.
 */
public class App1 {
    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int value;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.value = val;
        }
    }

    public static void main(String[] args) {
        int[] a = new int[10];
        TreeNode root = new TreeNode();
    }

    public void createTree(int[] arr, TreeNode root) {
        ArrayList<TreeNode> arrayList = new ArrayList<>();
        arrayList.add(root);
        int temp = 1;
        for (int i = 0; i < arr.length; i++) {
            int L = arr[i] / 100;
            int P = (arr[i] - L * 100) / 10;
            int V = (arr[i] - L * 100 - P * 10);
            if (L == temp) {
                root.left = new TreeNode(V);
                arrayList.add(root.left);
            }
        }
    }


}
