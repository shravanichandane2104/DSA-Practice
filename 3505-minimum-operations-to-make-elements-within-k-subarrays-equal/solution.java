// 287 ms | 103.9 MB
import java.util.*;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        // Coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int m = 0;
        for (int v : sorted) {
            if (m == 0 || sorted[m - 1] != v) {
                sorted[m++] = v;
            }
        }

        sorted = Arrays.copyOf(sorted, m);

        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[i] = Arrays.binarySearch(sorted, nums[i]) + 1;
        }

        // Calculate minimum cost for every window of size x
        Fenwick bit = new Fenwick(m);
        long[] cost = new long[n - x + 1];

        long windowSum = 0;

        for (int i = 0; i < n; i++) {
            bit.update(rank[i], 1, nums[i]);
            windowSum += nums[i];

            if (i >= x) {
                bit.update(rank[i - x], -1, -((long) nums[i - x]));
                windowSum -= nums[i - x];
            }

            if (i >= x - 1) {
                // Lower median
                int medianRank = bit.findKth((x + 1) / 2);
                long median = sorted[medianRank - 1];

                long leftCount = bit.queryCount(medianRank - 1);
                long leftSum = bit.querySum(medianRank - 1);

                long rightCount = x - bit.queryCount(medianRank);
                long rightSum = windowSum - bit.querySum(medianRank);

                cost[i - x + 1] =
                    median * leftCount - leftSum
                    + rightSum - median * rightCount;
            }
        }

        // DP: choose exactly j non-overlapping windows
        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        for (int j = 1; j <= k; j++) {
            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {
                // Skip current element
                curr[i] = curr[i - 1];

                // Select window ending at i - 1
                if (i >= x && prev[i - x] != INF) {
                    long candidate =
                        prev[i - x] + cost[i - x];

                    curr[i] = Math.min(curr[i], candidate);
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }

    class Fenwick {
        int n;
        int[] count;
        long[] sum;

        Fenwick(int n) {
            this.n = n;
            count = new int[n + 1];
            sum = new long[n + 1];
        }

        void update(int i, int c, long s) {
            while (i <= n) {
                count[i] += c;
                sum[i] += s;
                i += i & -i;
            }
        }

        int queryCount(int i) {
            int result = 0;

            while (i > 0) {
                result += count[i];
                i -= i & -i;
            }

            return result;
        }

        long querySum(int i) {
            long result = 0;

            while (i > 0) {
                result += sum[i];
                i -= i & -i;
            }

            return result;
        }

        int findKth(int k) {
            int index = 0;
            int accumulated = 0;

            for (int step = Integer.highestOneBit(n);
                 step > 0; step >>= 1) {

                int next = index + step;

                if (next <= n &&
                    accumulated + count[next] < k) {

                    index = next;
                    accumulated += count[next];
                }
            }

            return index + 1;
        }
    }
}