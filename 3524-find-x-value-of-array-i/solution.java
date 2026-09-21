// 15 ms | 92.1 MB
class Solution {
    public long[] resultArray(int[] nums, int k) {

        long[] ans = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {

            long[] next = new long[k];

            int rem = num % k;

            // Start a new subarray
            next[rem] = 1;

            // Extend previous subarrays
            for (int r = 0; r < k; r++) {

                int newRem = (int)((1L * r * rem) % k);

                next[newRem] += dp[r];
            }

            // Add all subarrays ending here
            for (int r = 0; r < k; r++) {
                ans[r] += next[r];
            }

            dp = next;
        }

        return ans;
    }
}