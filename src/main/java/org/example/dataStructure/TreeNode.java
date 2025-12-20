package org.example.dataStructure;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 二叉树节点定义
 */
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    /**
     * 从层序遍历数组创建二叉树（null表示空节点）
     */
    public static TreeNode fromArray(Integer[] nums) {
        if (nums == null || nums.length == 0 || nums[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(nums[0]);
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty() && i < nums.length) {
            TreeNode node = queue.poll();

            if (nums[i] != null) {
                node.left = new TreeNode(nums[i]);
                queue.offer(node.left);
            }
            i++;

            if (i < nums.length && nums[i] != null) {
                node.right = new TreeNode(nums[i]);
                queue.offer(node.right);
            }
            i++;
        }

        return root;
    }

    /**
     * 将树转换为层序数组以便于测试
     */
    public static Integer[] toArray(TreeNode root) {
        if (root == null) {
            return new Integer[0];
        }

        java.util.List<Integer> result = new java.util.ArrayList<>();
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                result.add(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            } else {
                result.add(null);
            }
        }

        // 移除尾部的null值
        while (!result.isEmpty() && result.getLast() == null) {
            result.removeLast();
        }

        return result.toArray(new Integer[0]);
    }
}
