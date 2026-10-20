// 78 ms | 71.6 MB
class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            int blocks = n / k;
            int[] min = new int[blocks];
            int[] max = new int[blocks];
            boolean sortable = true;

            for (int b = 0; b < blocks; b++) {
                int start = b * k;
                int end = start + k;
                int descents = 0;

                min[b] = Integer.MAX_VALUE;
                max[b] = Integer.MIN_VALUE;

                for (int j = start; j < end; j++) {
                    min[b] = Math.min(min[b], nums[j]);
                    max[b] = Math.max(max[b], nums[j]);

                    int next = (j + 1 == end) ? start : j + 1;
                    if (nums[j] > nums[next]) {
                        descents++;
                    }
                }

                if (descents > 1) {
                    sortable = false;
                    break;
                }
            }

            if (!sortable) {
                continue;
            }

            for (int b = 0; b < blocks - 1; b++) {
                if (max[b] > min[b + 1]) {
                    sortable = false;
                    break;
                }
            }

            if (sortable) {
                ans += k;
            }
        }

        return ans;
    }
}