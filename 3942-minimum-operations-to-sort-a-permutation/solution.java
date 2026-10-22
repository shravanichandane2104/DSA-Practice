// 4 ms | 94.5 MB

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }

        // Check whether nums is a rotation of sorted order
        boolean normal = true;
        for (int i = 0; i < n; i++) {
            if (nums[(i + zero) % n] != i) {
                normal = false;
                break;
            }
        }

        int ans = Integer.MAX_VALUE;

        if (normal) {
            ans = Math.min(zero, n - zero + 2);
        }

        // Check reversed orientation
        int r = (n - 1 - zero + n) % n;
        boolean reversed = true;

        for (int i = 0; i < n; i++) {
            int index = (n - 1 - i - r + 2 * n) % n;
            if (nums[index] != i) {
                reversed = false;
                break;
            }
        }

        if (reversed) {
            ans = Math.min(ans, Math.min(r + 1, n - r + 1));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
