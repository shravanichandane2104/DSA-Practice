/*
Problem: 104. Maximum Depth of Binary Tree
Platform: LeetCode
Difficulty: Easy
Pattern: Binary Tree + Recursion

Time Complexity: O(n)
Space Complexity: O(h)
*/

class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }

        return 1 + Math.max(
            maxDepth(root.left),
            maxDepth(root.right)
        );
    }
}
