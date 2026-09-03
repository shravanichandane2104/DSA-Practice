// 1 ms | 50.9 MB
class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }

    private int atMost(int[] nums, int k) {
        if (k < 0) return 0;

        int left = 0;
        int ans = 0;

        for (int right = 0; right < nums.length; right++) {

            k -= nums[right];

            while (k < 0) {
                k += nums[left];
                left++;
            }

            ans += right - left + 1;
        }

        return ans;
    }
}