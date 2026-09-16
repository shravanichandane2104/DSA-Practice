// 30 ms | 59.1 MB
class Solution {

    private static final int MOD = 1_000_000_007;

    public int numberOfSets(int n, int k) {

        int[][] f = new int[n + 1][k + 1];
        int[][] g = new int[n + 1][k + 1];

        // No segment is selected
        f[1][0] = 1;

        for (int i = 2; i <= n; i++) {

            for (int j = 0; j <= k; j++) {

                // Not currently drawing a segment
                f[i][j] = (f[i - 1][j] + g[i - 1][j]) % MOD;

                // Continue previous segment
                g[i][j] = g[i - 1][j];

                if (j > 0) {

                    // Start a new segment
                    g[i][j] += f[i - 1][j - 1];
                    g[i][j] %= MOD;

                    // Start/continue segment from previous state
                    g[i][j] += g[i - 1][j - 1];
                    g[i][j] %= MOD;
                }
            }
        }

        return (f[n][k] + g[n][k]) % MOD;
    }
}