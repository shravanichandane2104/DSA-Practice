/*
Problem: 704. Binary Search
Platform: LeetCode
Difficulty: Easy
Pattern: Binary Search

Time Complexity: O(log n)
Space Complexity: O(1)
*/

class BinarySearch {
    public int search(int[] nums, int target) {

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid;
            } 
            else if (nums[mid] < target) {
                low = mid + 1;
            } 
            else {
                high = mid - 1;
            }
        }

        return -1;
    }
}
