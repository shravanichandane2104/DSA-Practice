/*
Problem: 167. Two Sum II - Input Array Is Sorted
Platform: LeetCode
Difficulty: Medium
Pattern: Two Pointers

Time Complexity: O(n)
Space Complexity: O(1)
*/

class TwoSumII {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } 
            else if (sum < target) {
                left++;
            } 
            else {
                right--;
            }
        }

        return new int[]{};
    }
}
