/*
Problem: 11. Container With Most Water
Platform: LeetCode
Difficulty: Medium
Pattern: Two Pointers

Time Complexity: O(n)
Space Complexity: O(1)
*/

class ContainerWithMostWater {
    public int maxArea(int[] height) {

        int left = 0;
        int right = height.length - 1;
        int max = 0;

        while (left < right) {

            int area = Math.min(height[left], height[right]) * (right - left);

            max = Math.max(max, area);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }
}
