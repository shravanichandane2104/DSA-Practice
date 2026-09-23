// 4 ms | 101.5 MB
class Solution {
    public int minOperations(int[] nums, int x) {

        int n = nums.length;
        int total = 0;

        for (int num : nums) {
            total += num;
        }

        int target = total - x;

        // If target < 0, impossible
        if (target < 0) {
            return -1;
        }

        // If target == 0, remove all elements
        if (target == 0) {
            return n;
        }

        int left = 0;
        int sum = 0;
        int maxLen = -1;

        for (int right = 0; right < n; right++) {

            sum += nums[right];

            while (sum > target) {
                sum -= nums[left];
                left++;
            }

            if (sum == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

        if (maxLen == -1) {
            return -1;
        }

        return n - maxLen;
    }
}