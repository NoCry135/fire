package com.ca.fire.test.algorithm._26_30_dfs_bfs;


import com.ca.fire.test.algorithm.util.TreeNode;

/**
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 */
public class _29_OneZeroFour {
    /**
     * 递归
     * 也可以用广度优先搜索
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right));
    }

}
