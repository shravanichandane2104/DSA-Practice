// 192 ms | 91.7 MB
class Solution {
    static final int MOD = 1_000_000_007;

    long[] sum;
    long[] squareSum;
    long[] lazy;
    int n;

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new long[4 * n];
        squareSum = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int left = last[nums[i]];

            update(1, 0, n - 1, left, i);

            ans = (ans + squareSum[1]) % MOD;
            last[nums[i]] = i + 1;
        }

        return (int) ans;
    }

    private void update(int node, int start, int end,
                        int l, int r) {
        if (r < start || end < l) {
            return;
        }

        if (l <= start && end <= r) {
            apply(node, start, end, 1);
            return;
        }

        push(node, start, end);

        int mid = start + (end - start) / 2;

        update(node * 2, start, mid, l, r);
        update(node * 2 + 1, mid + 1, end, l, r);

        sum[node] =
            (sum[node * 2] + sum[node * 2 + 1]) % MOD;

        squareSum[node] =
            (squareSum[node * 2] + squareSum[node * 2 + 1]) % MOD;
    }

    private void apply(int node, int start, int end, long add) {
        long len = end - start + 1L;
        long oldSum = sum[node];

        squareSum[node] = (
            squareSum[node]
            + 2L * add % MOD * oldSum
            + add * add % MOD * len
        ) % MOD;

        sum[node] = (oldSum + add * len) % MOD;
        lazy[node] += add;
    }

    private void push(int node, int start, int end) {
        if (lazy[node] == 0 || start == end) {
            return;
        }

        int mid = start + (end - start) / 2;
        long add = lazy[node];

        apply(node * 2, start, mid, add);
        apply(node * 2 + 1, mid + 1, end, add);

        lazy[node] = 0;
    }
}